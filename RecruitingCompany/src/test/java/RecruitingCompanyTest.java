import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RecruitingCompanyTest {
    @Test
    void testConstructorAndInheritance() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        assertEquals(true, karriereAt instanceof JobSubject);
        assertEquals(0.0, karriereAt.getRevenue(), 0.001);
    }

    @Test
    void testPlaceOffer() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        JobOffer jobOfferBackendDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5965765;X;Junior Backend Software Developer;XXXLdigital;2;2342.0;;WEB,DB");
        JobOffer jobOfferDataScienceHigherPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5966440;;Data Scientist;voestalpine AG;3;3617.0; ;AI,DB");
        JobOffer jobOfferDataScience = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, new HashSet<Skill>(Arrays.asList(Skill.DB, Skill.AI)), true);
        JobOffer jobOfferDataScienceLowerPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5799902;;Data Scientist;7LYTIX GmbH;3;2556.0;3090.0;AI,DB");
        JobOffer jobOfferFullStack = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5971585;;Fullstack Entwickler  ;epunkt GmbH;3;3500.0;4500.0;WEB,SE,DB,QM");
        JobOffer jobOfferDataWarehouse = new JobOffer(5981234, "Data Warehouse im Lagerhouse", "Lagerhaus - die Kraft am Land", 3515.0, new HashSet<Skill>(Arrays.asList(Skill.DB)), false);
        JobOffer jobOfferDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/6008029;X;Senior Java Software Developer;Dynatrace Austria GmbH;4;3571.0;;SE");

        karriereAt.placeOffer(jobOfferBackendDev); //premium
        assertEquals(1000.0, karriereAt.getRevenue(), 0.001);

        karriereAt.placeOffer(jobOfferDataScienceHigherPay);
        assertEquals(1500.0, karriereAt.getRevenue(), 0.001);

        karriereAt.placeOffer(jobOfferDataScience); //premium
        assertEquals(2500.0, karriereAt.getRevenue(), 0.001);

        karriereAt.placeOffer(jobOfferDataScienceLowerPay);
        assertEquals(3000.0, karriereAt.getRevenue(), 0.001);

        karriereAt.placeOffer(jobOfferFullStack);
        assertEquals(3500.0, karriereAt.getRevenue(), 0.001);

        karriereAt.placeOffer(jobOfferDataWarehouse);
        assertEquals(4000.0, karriereAt.getRevenue(), 0.001);

        karriereAt.placeOffer(jobOfferDev); //premium
        assertEquals(5000.0, karriereAt.getRevenue(), 0.001);
    }

    @Test
    void testSendOutOffer() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        assertEquals(false, karriereAt.sendOutOffer());

        JobOffer jobOfferBackendDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5965765;X;Junior Backend Software Developer;XXXLdigital;2;2342.0;;WEB,DB");
        JobOffer jobOfferDataScienceHigherPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5966440;;Data Scientist;voestalpine AG;3;3617.0; ;AI,DB");
        JobOffer jobOfferDataScience = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, new HashSet<Skill>(Arrays.asList(Skill.DB, Skill.AI)), true);
        JobOffer jobOfferDataScienceLowerPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5799902;;Data Scientist;7LYTIX GmbH;3;2556.0;3090.0;AI,DB");
        JobOffer jobOfferFullStack = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5971585;;Fullstack Entwickler  ;epunkt GmbH;3;3500.0;4500.0;WEB,SE,DB,QM");
        JobOffer jobOfferDataWarehouse = new JobOffer(5981234, "Data Warehouse im Lagerhouse", "Lagerhaus - die Kraft am Land", 3515.0, new HashSet<Skill>(Arrays.asList(Skill.DB)), false);
        JobOffer jobOfferDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/6008029;X;Senior Java Software Developer;Dynatrace Austria GmbH;4;3571.0;;SE");

        karriereAt.placeOffer(jobOfferBackendDev);
        karriereAt.placeOffer(jobOfferDataScienceHigherPay);
        karriereAt.placeOffer(jobOfferDataScience);
        karriereAt.placeOffer(jobOfferDataScienceLowerPay);
        karriereAt.placeOffer(jobOfferFullStack);
        karriereAt.placeOffer(jobOfferDataWarehouse);
        karriereAt.placeOffer(jobOfferDev);

        assertEquals(true, karriereAt.sendOutOffer());
        assertEquals(true, karriereAt.sendOutOffer());
        assertEquals(true, karriereAt.sendOutOffer());
        assertEquals(true, karriereAt.sendOutOffer());
        assertEquals(true, karriereAt.sendOutOffer());
        assertEquals(true, karriereAt.sendOutOffer());
        assertEquals(true, karriereAt.sendOutOffer());

        assertEquals(false, karriereAt.sendOutOffer());
    }

    @Test
    void testSendOutOfferWithSubscribedObservers() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        Applicant applicantDataScience = new Applicant("Doris Datamayr", 3400);
        applicantDataScience.addSkill(Skill.AI);
        applicantDataScience.addSkill(Skill.DB);
        karriereAt.registerObserver(applicantDataScience);

        Applicant applicantFullStack = new Applicant("Fullhilde Stackinger", 3900);
        applicantFullStack.addSkill(Skill.QM);
        applicantFullStack.addSkill(Skill.SE);
        applicantFullStack.addSkill(Skill.WEB);
        applicantFullStack.addSkill(Skill.DB);
        applicantFullStack.addSkill(Skill.PM);
        karriereAt.registerObserver(applicantFullStack);

        JobOffer jobOfferBackendDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5965765;X;Junior Backend Software Developer;XXXLdigital;2;2342.0;;WEB,DB");
        JobOffer jobOfferDataScienceHigherPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5966440;;Data Scientist;voestalpine AG;3;3617.0; ;AI,DB");
        JobOffer jobOfferDataScience = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, new HashSet<Skill>(Arrays.asList(Skill.DB, Skill.AI)), true);
        JobOffer jobOfferDataScienceLowerPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5799902;;Data Scientist;7LYTIX GmbH;3;2556.0;3090.0;AI,DB");
        JobOffer jobOfferFullStack = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5971585;;Fullstack Entwickler  ;epunkt GmbH;3;3500.0;4500.0;WEB,SE,DB,QM");
        JobOffer jobOfferDataWarehouse = new JobOffer(5981234, "Data Warehouse im Lagerhouse", "Lagerhaus - die Kraft am Land", 3515.0, new HashSet<Skill>(Arrays.asList(Skill.DB)), false);
        JobOffer jobOfferDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/6008029;X;Senior Java Software Developer;Dynatrace Austria GmbH;4;3571.0;;SE");

        karriereAt.placeOffer(jobOfferBackendDev);
        karriereAt.placeOffer(jobOfferDataScienceHigherPay);
        karriereAt.placeOffer(jobOfferDataScience);
        karriereAt.placeOffer(jobOfferDataScienceLowerPay);
        karriereAt.placeOffer(jobOfferFullStack);
        karriereAt.placeOffer(jobOfferDataWarehouse);
        karriereAt.placeOffer(jobOfferDev);

        assertEquals(5000.0, karriereAt.getRevenue(), 0.001);

        karriereAt.sendOutOffer(); //jobOfferDev

        assertEquals(5200.0, karriereAt.getRevenue(), 0.001);
        assertEquals(0.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScience

        assertEquals(5400.0, karriereAt.getRevenue(), 0.001);
        assertEquals(4785.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferBackendDev

        assertEquals(5400.0, karriereAt.getRevenue(), 0.001);
        assertEquals(4785.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScienceHigherPay

        assertEquals(5600.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataWarehouse

        assertEquals(6000.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferFullStack

        assertEquals(6200.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScienceLowerPay

        assertEquals(6200.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantFullStack.getRelevantJobOfferCount());
    }

    @Test
    void testSendOutOfferWithDuplicateObservers() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        Applicant applicantDataScience = new Applicant("Doris Datamayr", 3400);
        applicantDataScience.addSkill(Skill.AI);
        applicantDataScience.addSkill(Skill.DB);

        karriereAt.registerObserver(applicantDataScience);
        karriereAt.registerObserver(applicantDataScience);

        JobOffer jobOfferBackendDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5965765;X;Junior Backend Software Developer;XXXLdigital;2;2342.0;;WEB,DB");
        JobOffer jobOfferDataScienceHigherPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5966440;;Data Scientist;voestalpine AG;3;3617.0; ;AI,DB");
        JobOffer jobOfferDataScience = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, new HashSet<Skill>(Arrays.asList(Skill.DB, Skill.AI)), true);
        JobOffer jobOfferDataScienceLowerPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5799902;;Data Scientist;7LYTIX GmbH;3;2556.0;3090.0;AI,DB");
        JobOffer jobOfferFullStack = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5971585;;Fullstack Entwickler  ;epunkt GmbH;3;3500.0;4500.0;WEB,SE,DB,QM");
        JobOffer jobOfferDataWarehouse = new JobOffer(5981234, "Data Warehouse im Lagerhouse", "Lagerhaus - die Kraft am Land", 3515.0, new HashSet<Skill>(Arrays.asList(Skill.DB)), false);
        JobOffer jobOfferDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/6008029;X;Senior Java Software Developer;Dynatrace Austria GmbH;4;3571.0;;SE");

        karriereAt.placeOffer(jobOfferBackendDev);
        karriereAt.placeOffer(jobOfferDataScienceHigherPay);
        karriereAt.placeOffer(jobOfferDataScience);
        karriereAt.placeOffer(jobOfferDataScienceLowerPay);
        karriereAt.placeOffer(jobOfferFullStack);
        karriereAt.placeOffer(jobOfferDataWarehouse);
        karriereAt.placeOffer(jobOfferDev);

        assertEquals(5000.0, karriereAt.getRevenue(), 0.001);

        karriereAt.sendOutOffer(); //jobOfferDev

        assertEquals(5000.0, karriereAt.getRevenue(), 0.001);
        assertEquals(0.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantDataScience.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScience

        assertEquals(5200.0, karriereAt.getRevenue(), 0.001);
        assertEquals(4785.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantDataScience.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferBackendDev

        assertEquals(5200.0, karriereAt.getRevenue(), 0.001);
        assertEquals(4785.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantDataScience.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScienceHigherPay

        assertEquals(5400.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantDataScience.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataWarehouse

        assertEquals(5600.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantDataScience.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferFullStack

        assertEquals(5600.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantDataScience.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScienceLowerPay

        assertEquals(5600.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantDataScience.getRelevantJobOfferCount());
    }

    @Test
    void testSendOutOfferWithUnsubscribedObservers() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        Applicant applicantDataScience = new Applicant("Doris Datamayr", 3400);
        applicantDataScience.addSkill(Skill.AI);
        applicantDataScience.addSkill(Skill.DB);
        karriereAt.registerObserver(applicantDataScience);

        Applicant applicantFullStack = new Applicant("Fullhilde Stackinger", 3900);
        applicantFullStack.addSkill(Skill.QM);
        applicantFullStack.addSkill(Skill.SE);
        applicantFullStack.addSkill(Skill.WEB);
        applicantFullStack.addSkill(Skill.DB);
        applicantFullStack.addSkill(Skill.PM);

        JobOffer jobOfferBackendDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5965765;X;Junior Backend Software Developer;XXXLdigital;2;2342.0;;WEB,DB");
        JobOffer jobOfferDataScienceHigherPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5966440;;Data Scientist;voestalpine AG;3;3617.0; ;AI,DB");
        JobOffer jobOfferDataScience = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, new HashSet<Skill>(Arrays.asList(Skill.DB, Skill.AI)), true);
        JobOffer jobOfferDataScienceLowerPay = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5799902;;Data Scientist;7LYTIX GmbH;3;2556.0;3090.0;AI,DB");
        JobOffer jobOfferFullStack = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5971585;;Fullstack Entwickler  ;epunkt GmbH;3;3500.0;4500.0;WEB,SE,DB,QM");
        JobOffer jobOfferDataWarehouse = new JobOffer(5981234, "Data Warehouse im Lagerhouse", "Lagerhaus - die Kraft am Land", 3515.0, new HashSet<Skill>(Arrays.asList(Skill.DB)), false);
        JobOffer jobOfferDev = JobOfferFactory.createFromString("https://www.karriere.at/jobs/6008029;X;Senior Java Software Developer;Dynatrace Austria GmbH;4;3571.0;;SE");

        karriereAt.placeOffer(jobOfferBackendDev);
        karriereAt.placeOffer(jobOfferDataScienceHigherPay);
        karriereAt.placeOffer(jobOfferDataScience);
        karriereAt.placeOffer(jobOfferDataScienceLowerPay);
        karriereAt.placeOffer(jobOfferFullStack);
        karriereAt.placeOffer(jobOfferDataWarehouse);
        karriereAt.placeOffer(jobOfferDev);

        assertEquals(5000.0, karriereAt.getRevenue(), 0.001);

        karriereAt.sendOutOffer(); //jobOfferDev

        assertEquals(5000.0, karriereAt.getRevenue(), 0.001);
        assertEquals(0.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(0, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScience

        assertEquals(5200.0, karriereAt.getRevenue(), 0.001);
        assertEquals(4785.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(0, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferBackendDev

        assertEquals(5200.0, karriereAt.getRevenue(), 0.001);
        assertEquals(4785.0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(0, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.registerObserver(applicantFullStack);

        karriereAt.sendOutOffer(); //jobOfferDataScienceHigherPay

        assertEquals(5400.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(0, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.unregisterObserver(applicantDataScience);

        karriereAt.sendOutOffer(); //jobOfferDataWarehouse

        assertEquals(5600.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5272.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferFullStack

        assertEquals(5800.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5272.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantFullStack.getRelevantJobOfferCount());

        karriereAt.sendOutOffer(); //jobOfferDataScienceLowerPay

        assertEquals(5800.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5272.5, applicantFullStack.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantFullStack.getRelevantJobOfferCount());
    }

    @Test
    void testPlaceOfferFromFile() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        karriereAt.placeOffersFromFile("data/jobs.csv");

        assertEquals(14000, karriereAt.getRevenue());

        for(int i =0; i < 20; i++) {
            assertEquals(true, karriereAt.sendOutOffer());
        }

        assertEquals(false, karriereAt.sendOutOffer());
    }

    @Test
    void testPlaceOfferFromFileWithObservers() {
        RecruitingCompany karriereAt = new RecruitingCompany();

        Applicant applicantDataScience = new Applicant("Doris Datamayr", 3400);
        applicantDataScience.addSkill(Skill.AI);
        applicantDataScience.addSkill(Skill.DB);
        karriereAt.registerObserver(applicantDataScience);

        Applicant applicantWebDeveloper = new Applicant("Weronika Webdewowicz", 3290.0);
        applicantWebDeveloper.addSkill(Skill.WEB);
        applicantWebDeveloper.addSkill(Skill.SE);
        applicantWebDeveloper.addSkill(Skill.DB);
        karriereAt.registerObserver(applicantWebDeveloper);

        Applicant applicantTestEngineer = new Applicant("Jay Unit", 3000.0);
        applicantTestEngineer.addSkill(Skill.QM);
        applicantTestEngineer.addSkill(Skill.SE);
        karriereAt.registerObserver(applicantTestEngineer);

        Applicant applicantConsulting = new Applicant("Carla Consultant", 3900.0);
        applicantConsulting.addSkill(Skill.PM);
        applicantConsulting.addSkill(Skill.CON);
        karriereAt.registerObserver(applicantConsulting);

        Applicant applicantHacker = new Applicant("Helga Hacker", 2000.0);
        applicantHacker.addSkill(Skill.IT);
        applicantHacker.addSkill(Skill.SEC);
        karriereAt.registerObserver(applicantHacker);

        karriereAt.placeOffersFromFile("data/jobs.csv");

        assertEquals(14000.0, karriereAt.getRevenue(), 0.001);
        assertEquals(0, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(0, applicantWebDeveloper.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantWebDeveloper.getRelevantJobOfferCount());
        assertEquals(0, applicantTestEngineer.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantTestEngineer.getRelevantJobOfferCount());
        assertEquals(0, applicantConsulting.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantConsulting.getRelevantJobOfferCount());
        assertEquals(0, applicantHacker.getHighestPossiblePay(), 0.001);
        assertEquals(0, applicantHacker.getRelevantJobOfferCount());

        for(int i =0; i < 20; i++) {
            karriereAt.sendOutOffer();
        }

        assertEquals(17600.0, karriereAt.getRevenue(), 0.001);
        assertEquals(5425.5, applicantDataScience.getHighestPossiblePay(), 0.001);
        assertEquals(3, applicantDataScience.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantWebDeveloper.getHighestPossiblePay(), 0.001);
        assertEquals(6, applicantWebDeveloper.getRelevantJobOfferCount());
        assertEquals(5356.5, applicantTestEngineer.getHighestPossiblePay(), 0.001);
        assertEquals(6, applicantTestEngineer.getRelevantJobOfferCount());
        assertEquals(4500.0, applicantConsulting.getHighestPossiblePay(), 0.001);
        assertEquals(1, applicantConsulting.getRelevantJobOfferCount());
        assertEquals(5251.5, applicantHacker.getHighestPossiblePay(), 0.001);
        assertEquals(2, applicantHacker.getRelevantJobOfferCount());
    }
}