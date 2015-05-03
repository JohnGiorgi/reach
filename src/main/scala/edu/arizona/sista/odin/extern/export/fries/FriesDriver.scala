package edu.arizona.sista.odin.extern.export.fries

import java.io._

import edu.arizona.sista.processors.{DocumentSerializer, Document}
import edu.arizona.sista.processors.bionlp.BioNLPProcessor
import edu.arizona.sista.odin._
import edu.arizona.sista.odin.domains.bigmechanism.dryrun2015.{DarpaActions,Ruler}
import edu.arizona.sista.odin.extern.inward._

import org.slf4j.LoggerFactory

/**
  * Top-level test driver for Fries output development.
  *   Author: by Tom Hicks. 4/30/2015.
  *   Last Modified: Fake doc ID in document.
  */
object FriesDriver extends App {
  val logger = LoggerFactory.getLogger(this.getClass.getSimpleName)

  val entityRules = Ruler.readEntityRules()
  val eventRules = Ruler.readEventRules()
  val rules = entityRules + "\n\n" + eventRules

  val ds = new DocumentSerializer
  val actions = new DarpaActions
  val processor = new BioNLPProcessor()
  val extractor = new Ruler(rules, actions)

  val PapersDir = s"${System.getProperty("user.dir")}/src/test/resources/papers/"
  val paperNames = Seq(
    "MEKinhibition.txt.ser",
    "UbiquitinationofRas.txt.ser",
    "PMC3441633.txt.ser",
    "PMC3847091.txt.ser"
  )

  def cleanText (m: Mention): String = {
    """(\s+|\n|\t|[;])""".r.replaceAllIn(m.document.sentences(m.sentence).getSentenceText(), " ")
  }

  def docFromSerializedFile (filename: String): Document = {
    val br = new BufferedReader(new FileReader(filename))
    val doc = ds.load(br)
    doc
  }

  def getText(fileName: String):String = scala.io.Source.fromFile(fileName).mkString

  // val outDir = s"${System.getProperty("java.io.tmpdir")}" + File.separator
  val outDir = s"${System.getProperty("user.dir")}" + File.separator
  def mkOutputName (paper:String, ext:String): String = {
    outDir + {"""^.*?/|.txt.ser""".r.replaceAllIn(paper, "")} + ext
  }

  def processPapers (papers:Seq[String], asStrings:Boolean=false) = {
    papers.foreach { paper => processPaper(paper, asStrings) }
  }

  def processPaper (paper: String, asStrings:Boolean=false) = {
    val ext = if (asStrings) ".txt" else ".json"
    val outName = mkOutputName(paper, ext)
    val outFile = new FileOutputStream(new File(outName))
    val inFile = s"$PapersDir/$paper"

    val doc = paper match {
      case ser if ser.endsWith("ser") => docFromSerializedFile(inFile)
      case _ => processor.annotate(getText(inFile))
    }
    doc.id = Some(paper)                    // fake document ID for now

    val mentions = extractor.extractFrom(doc)
    val sortedMentions = mentions.sortBy(m => (m.sentence, m.start)) // sort by sentence, start idx
    if (asStrings)
      outputEventMentions(sortedMentions, doc, outFile)
    else
      outputFries(sortedMentions, doc, outFile)
  }

  /** Output string representations for the given sequence of mentions. */
  def outputAllMentions (mentions:Seq[Mention], doc:Document, fos:FileOutputStream) = {
    val out:PrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fos)))
    val menMgr:MentionManager = new MentionManager()
    mentions.foreach { m =>
      menMgr.mentionToStrings(m).foreach { str => out.println(str) }
    }
    out.flush()
    out.close()
  }

  /** Output a FRIES representation for the given sequence of mentions. */
  def outputFries (mentions:Seq[Mention], doc:Document, out:FileOutputStream) = {
    val frier:FriesOutput = new FriesOutput()
    frier.toJSON(mentions, doc, out)
    out.flush()
    out.close()
  }

  /** Output string representations for event mentions in the given sequence. */
  def outputEventMentions (mentions:Seq[Mention], doc:Document, fos:FileOutputStream) = {
    val menMgr:MentionManager = new MentionManager()
    menMgr.outputSelectedMentions("Event", mentions, fos)
  }


  // Top-level Main of script:
  var asStrings:Boolean = false
  if (!args.isEmpty) asStrings = true
  processPapers(paperNames, asStrings)

}
