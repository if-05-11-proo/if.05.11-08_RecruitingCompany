import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantTest {
    @Test
    void testInheritance() {
        Applicant applicant = new Applicant("Pamela Project-Manager", 5115.0);
        assertEquals(true, applicant instanceof JobObserver);
    }

    @Test
    void testConstructorAndGetters() {
        Applicant applicant = new Applicant("Carla Consultant", 3795.9);

        assertEquals("Carla Consultant", applicant.getName());
        assertEquals(3795.9, applicant.getDesiredPay());
        assertEquals(0, applicant.getHighestPossiblePay());
        assertEquals(0, applicant.getRelevantJobOfferCount());
    }

    @Test
    void testSkills() {
        Applicant applicant = new Applicant("Doris Datamayr", 3500);

        Collection<Skill> skillsOnlyDatabase = Arrays.asList(Skill.DB);
        Collection<Skill> skillsWebDev = Arrays.asList(Skill.WEB, Skill.SE, Skill.DB);
        Collection<Skill> skillsDataScience = Arrays.asList(Skill.AI, Skill.DB);

        assertEquals(false, applicant.hasSkills(skillsWebDev));
        assertEquals(false, applicant.hasSkills(skillsDataScience));
        assertEquals(false, applicant.hasSkills(skillsOnlyDatabase));

        assertEquals(true, applicant.addSkill(Skill.DB));

        assertEquals(false, applicant.hasSkills(skillsWebDev));
        assertEquals(false, applicant.hasSkills(skillsDataScience));
        assertEquals(true, applicant.hasSkills(skillsOnlyDatabase));

        assertEquals(false, applicant.addSkill(Skill.DB));

        assertEquals(false, applicant.hasSkills(skillsWebDev));
        assertEquals(false, applicant.hasSkills(skillsDataScience));
        assertEquals(true, applicant.hasSkills(skillsOnlyDatabase));

        assertEquals(true, applicant.addSkill(Skill.SE));

        assertEquals(false, applicant.hasSkills(skillsWebDev));
        assertEquals(false, applicant.hasSkills(skillsDataScience));
        assertEquals(true, applicant.hasSkills(skillsOnlyDatabase));

        assertEquals(true, applicant.addSkill(Skill.AI));

        assertEquals(false, applicant.hasSkills(skillsWebDev));
        assertEquals(true, applicant.hasSkills(skillsDataScience));
        assertEquals(true, applicant.hasSkills(skillsOnlyDatabase));
    }

    @Test
    void testOfferJob() {
        Applicant applicant = new Applicant("Doris Datamayr", 3400);
        applicant.addSkill(Skill.AI);
        applicant.addSkill(Skill.DB);

        JobOffer jobOfferBackendDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5965765;X;Junior Backend Software Developer;XXXLdigital;2;2342.0;;WEB,DB");
        JobOffer jobOfferDataScienceHigherPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5966440;;Data Scientist;voestalpine AG;3;3617.0; ;AI,DB");
        JobOffer jobOfferDataScience = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, new HashSet<Skill>(Arrays.asList(Skill.DB, Skill.AI)), true);
        JobOffer jobOfferDataScienceLowerPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5799902;;Data Scientist;7LYTIX GmbH;3;2556.0;3090.0;AI,DB");
        JobOffer jobOfferFullStack = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5971585;;Fullstack Entwickler  ;epunkt GmbH;3;3500.0;4500.0;WEB,SE,DB,QM");
        JobOffer jobOfferDataWarehouse = new JobOffer(5981234, "Data Warehouse SpezialistIn", "Lagerhaus - die Kraft am Land", 3515.0, new HashSet<Skill>(Arrays.asList(Skill.DB)), false);
        JobOffer jobOfferDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/6008029;X;Senior Java Software Developer;Dynatrace Austria GmbH;4;3571.0;;SE");

        assertEquals(false, applicant.offerJob(jobOfferBackendDev));
        assertEquals(0, applicant.getHighestPossiblePay());
        assertEquals(0, applicant.getRelevantJobOfferCount());

        assertEquals(true, applicant.offerJob(jobOfferDataScience));
        assertEquals(4785.0, applicant.getHighestPossiblePay());
        assertEquals(1, applicant.getRelevantJobOfferCount());

        assertEquals(false, applicant.offerJob(jobOfferFullStack));
        assertEquals(4785.0, applicant.getHighestPossiblePay());
        assertEquals(1, applicant.getRelevantJobOfferCount());

        assertEquals(false, applicant.offerJob(jobOfferDataScienceLowerPay));
        assertEquals(4785.0, applicant.getHighestPossiblePay());
        assertEquals(1, applicant.getRelevantJobOfferCount());

        assertEquals(true, applicant.offerJob(jobOfferDataWarehouse));
        assertEquals(5272.5, applicant.getHighestPossiblePay());
        assertEquals(2, applicant.getRelevantJobOfferCount());

        assertEquals(false, applicant.offerJob(jobOfferDev));
        assertEquals(5272.5, applicant.getHighestPossiblePay());
        assertEquals(2, applicant.getRelevantJobOfferCount());

        assertEquals(true, applicant.offerJob(jobOfferDataScienceHigherPay));
        assertEquals(5425.5, applicant.getHighestPossiblePay());
        assertEquals(3, applicant.getRelevantJobOfferCount());
    }
}