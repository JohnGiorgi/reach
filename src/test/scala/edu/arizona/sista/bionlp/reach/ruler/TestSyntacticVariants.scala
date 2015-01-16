package edu.arizona.sista.bionlp.reach.ruler

import edu.arizona.sista.bionlp.reach.ruler.DarpaEvalUtils._
import org.junit.Assert._
import org.junit.Test
import TestResources.{bioproc, extractor}

/**
 * Created by dane on 1/14/15.
 * Testing for common syntactic variations on rules, e.g. passive voice, relative clauses, etc.
 */
class TestSyntacticVariants {

  @Test def testHydrolysisDecl1() {
    val doc = bioproc.annotate("RasGAP is hydrolyzing GTP to GDP in Ras reactions.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GTP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisSubjectDecl1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisPass1() {
    val doc = bioproc.annotate("Ras-GDP is hydrolyzed by 26S proteasome without ubiquitination.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GDP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisSubjectPass1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisSubjNom1() {
    val doc = bioproc.annotate("MEK hydrolysis of Ras-GDP increased.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GDP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisSubjNom1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisObjNom1() {
    val doc = bioproc.annotate("Ras-GDP hydrolysis by MEK increased.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GDP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisObjNom1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisSubjectRel1() {
    val doc = bioproc.annotate("Its many abnormal phenotypes can be rescued via Pde2, which specifically hydrolyzes Ras-GDP.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GDP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisSubjectRel1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisSubjectRel2() {
    val doc = bioproc.annotate("Pde2, which has been found to hydrolyze Ras-GDP, activates MEK.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GDP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisSubjectRel2")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisSubjectRelApposition1() {
    val doc = bioproc.annotate("Its many abnormal phenotypes can be rescued via overexpressing Pde2, a phosphodiesterase that specifically hydrolyzes Ras-GDP.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GDP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisSubjectRelApposition1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisSubjectRelApposition2() {
    val doc = bioproc.annotate("A main rate-controlling step in RAS is renin, an enzyme that hydrolyzes Ras-GTP to generate angiotensin I.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("Ras-GTP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisSubjectApposition2")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testHydrolysisObjectRel1() {
    val doc = bioproc.annotate("We measured transcription activation in the presence of MEK, which is hydrolyzed by CRP.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("hydrolysis (DANE)", hasEventWithArguments("Hydrolysis", List("CRP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testHydrolysisObjectRel1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingDecl1() {
    val doc = bioproc.annotate("Mechanistically, ASPP1 and ASPP2 bind RAS-GTP.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("ASPP1", "ASPP2", "RAS-GTP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingDecl1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingDecl2() {
    val doc = bioproc.annotate("Mechanistically, ASPP1 and ASPP2 bind with RAS-GTP.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("ASPP1", "ASPP2", "RAS-GTP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingDecl2")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingPass1() {
    val doc = bioproc.annotate("Mechanistically, ASPP1 and ASPP2 are bound by RAS-GTP.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("ASPP1", "ASPP2", "RAS-GTP"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingPass1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingPrepNom1() {
    val doc = bioproc.annotate("We detected elevated binding of p53 to K-Ras.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("p53", "K-Ras"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingPrepNom1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingPrepNom2() {
    val doc = bioproc.annotate("We detected elevated binding of p53 and K-Ras.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("p53", "K-Ras"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingPrepNom2")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingPrepNom3() {
    val doc = bioproc.annotate("We detected elevated binding of p53 with K-Ras.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("p53", "K-Ras"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingPrepNom3")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingSubjNom1() {
    val doc = bioproc.annotate("We detected elevated p53 binding to K-Ras.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("p53", "K-Ras"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingSubjNom1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingObjNom1() {
    val doc = bioproc.annotate("We detected elevated K-Ras binding by p53.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("p53", "K-Ras"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingObjNom1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingSubjRel1() {
    val doc = bioproc.annotate("We detected elevated phosphorylation of K-Ras, a protein that subsequently binds p53.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("p53", "K-Ras"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingSubjRel1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testBindingObjRel1() {
    val doc = bioproc.annotate("We detected elevated phosphorylation of K-Ras, a protein that is subsequently bound by p53.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("binding (MARCO/GUS)", hasEventWithArguments("Binding", List("p53", "K-Ras"), mentions))
    } catch {
      case e: AssertionError =>
        header("testBindingObjRel1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testTransport1() {
    val doc = bioproc.annotate("Phosphorylation leads the plasma membrane to release p53 to the cytosol.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("transport (ENRIQUE)", hasEventWithArguments("Transport", List("p53", "plasma membrane", "cytosol"), mentions))
    } catch {
      case e: AssertionError =>
        header("testTransport1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testTransport2() {
    val doc = bioproc.annotate("Recruitment of p53 from the cytosol to the plasma membrane increases with phosphorylation.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("transport (ENRIQUE)", hasEventWithArguments("Transport", List("p53", "plasma membrane", "cytosol"), mentions))
    } catch {
      case e: AssertionError =>
        header("testTransport2")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testTransport3() {
    val doc = bioproc.annotate("With increased phosphorylation, p53 is exported from the plasma membrane to the cytosol.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("transport (ENRIQUE)", hasEventWithArguments("Transport", List("p53", "plasma membrane", "cytosol"), mentions))
    } catch {
      case e: AssertionError =>
        header("testTransport3")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testTransport4() {
    val doc = bioproc.annotate("ASPP2, a protein which is transported from the membrane to the nucleus, is subsequently phosphorylated.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("transport (ENRIQUE)", hasEventWithArguments("Transport", List("ASPP2", "membrane", "nucleus"), mentions))
    } catch {
      case e: AssertionError =>
        header("testTransport4")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testTransport5() {
    val doc = bioproc.annotate("ASPP2, a protein which translocates Pde2 from the membrane to the nucleus, is subsequently phosphorylated.")
    val mentions = extractor.extractFrom(doc)

    try {
      assertTrue("transport (ENRIQUE)", hasEventWithArguments("Transport", List("Pde2", "membrane", "nucleus"), mentions))
    } catch {
      case e: AssertionError =>
        header("testTransport4")
        displayMentions(mentions, doc)
        throw e
    }
  }

  // Phospho tests
  @Test def testPhosphorylationDecl1() {
    val doc = bioproc.annotate("Ras is phosphorylating ASPP2.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}SubjectDecl1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationPass1() {
    val doc = bioproc.annotate("ASPP2 is phosphorylated by Ras.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}SubjectPass1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationSubjNom1() {
    val doc = bioproc.annotate("Ras phosphorylation of ASPP2 increased.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}SubjNom1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationObjNom1() {
    val doc = bioproc.annotate("ASPP2 phosphorylation by Ras increased.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"regulation ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header("testPhosphorylationObjNom1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationSubjectRel1() {
    val doc = bioproc.annotate("Its many abnormal phenotypes can be rescued via Ras, which specifically phosphorylates ASPP2.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"regulation ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}SubjectRel1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationSubjectRel2() {
    val doc = bioproc.annotate("Ras, which has been found to phosphorylate ASPP2, activates MEK.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"regulation ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}SubjectRel2")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationSubjectRelApposition1() {
    val doc = bioproc.annotate("Its many abnormal phenotypes can be rescued via overexpressing Ras, an XXX that specifically phosphorylates ASPP2.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"regulation ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}SubjectRelApposition1")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationSubjectRelApposition2() {
    val doc = bioproc.annotate("A main rate-controlling step in AAAA is renin, an enzyme that phosphorylates ASPP2 to generate XXXX")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}SubjectApposition2")
        displayMentions(mentions, doc)
        throw e
    }
  }

  @Test def testPhosphorylationObjectRel1() {
    val doc = bioproc.annotate("We measured transcription activation in the presence of ASPP2, which is phosphorylated by Ras.")
    val mentions = extractor.extractFrom(doc)
    val eventLabel = "Phosphorylation"
    val assignedParty = "GUS"

    try {
      assertTrue(s"${eventLabel.toLowerCase} ($assignedParty)", hasEventWithArguments(eventLabel, List("ASPP2"), mentions))
      assertTrue(s"up-regulation ($assignedParty)", hasUpRegulationByEntity("Ras", eventLabel, List("ASPP2"), mentions))
    } catch {
      case e: AssertionError =>
        header(s"test${eventLabel}ObjectRel1")
        displayMentions(mentions, doc)
        throw e
    }
  }
}

