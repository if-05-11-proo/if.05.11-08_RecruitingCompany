import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobOfferTest {
    @Test
    void testConstructors() {
        HashSet<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.SE);

        JobOffer jobOffer = new JobOffer(6008029, "Senior Java Software Developer", "Dynatrace Austria GmbH", 3571.0, requiredSkills, true);

        assertEquals(6008029, jobOffer.getId());
        assertEquals("Senior Java Software Developer", jobOffer.getDescription());
        assertEquals("Dynatrace Austria GmbH", jobOffer.getCompany());
        assertEquals(3571.0, jobOffer.getMinPay(), 0.001);
        assertEquals(5356.5, jobOffer.getMaxPay(), 0.001);
        assertEquals(requiredSkills, jobOffer.getRequiredSkills());
        assertEquals(true, jobOffer.isPremium());

        requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.QM);
        requiredSkills.add(Skill.SE);

        jobOffer = new JobOffer(6009490, "Software Test Engineer", "karriere.at", 3190.0, 4000.0, requiredSkills, false);

        assertEquals(6009490, jobOffer.getId());
        assertEquals("Software Test Engineer", jobOffer.getDescription());
        assertEquals("karriere.at", jobOffer.getCompany());
        assertEquals(3190.0, jobOffer.getMinPay(), 0.001);
        assertEquals(4000.0, jobOffer.getMaxPay(), 0.001);
        assertEquals(requiredSkills, jobOffer.getRequiredSkills());
        assertEquals(false, jobOffer.isPremium());

        requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.WEB);
        requiredSkills.add(Skill.SE);

        jobOffer = new JobOffer(6001974, "Junior Web Developer", "Fancy Agency", 1614.0, 1989.99, requiredSkills, false);

        assertEquals(6001974, jobOffer.getId());
        assertEquals("Junior Web Developer", jobOffer.getDescription());
        assertEquals("Fancy Agency", jobOffer.getCompany());
        assertEquals(1614.0, jobOffer.getMinPay(), 0.001);
        assertEquals(1989.99, jobOffer.getMaxPay(), 0.001);
        assertEquals(requiredSkills, jobOffer.getRequiredSkills());
        assertEquals(false, jobOffer.isPremium());

        requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.AI);
        requiredSkills.add(Skill.DB);

        jobOffer = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, 3190.0, requiredSkills, true);

        assertEquals(5970433, jobOffer.getId());
        assertEquals("Data Scientist - Vehicle Data", jobOffer.getDescription());
        assertEquals("KTM Innovation GmbH", jobOffer.getCompany());
        assertEquals(3190.0, jobOffer.getMinPay(), 0.001);
        assertEquals(3190.0, jobOffer.getMaxPay(), 0.001);
        assertEquals(requiredSkills, jobOffer.getRequiredSkills());
        assertEquals(true, jobOffer.isPremium());
    }

    @Test
    void testConstructorsIllegalPay() {
        HashSet<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.SE);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            JobOffer jobOffer = new JobOffer(6008029, "Senior Java Software Developer", "Dynatrace Austria GmbH", -123.45, requiredSkills, true);
        });
        assertEquals("Invalid pay specified!", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            JobOffer jobOffer = new JobOffer(6001974, "Junior Web Developer", "Fancy Agency", 1613.5, 1900.0, requiredSkills, false);
        });
        assertEquals("Invalid pay specified!", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            JobOffer jobOffer = new JobOffer(6008029, "Senior Java Software Developer", "Dynatrace Austria GmbH", 0, 2500.0, requiredSkills, true);
        });
        assertEquals("Invalid pay specified!", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            JobOffer jobOffer = new JobOffer(6001974, "Junior Web Developer", "Fancy Agency", 1615.0, 1614.0, requiredSkills, false);
        });
        assertEquals("Invalid pay specified!", e.getMessage());
    }

    @Test
    void testSettersIllegalPay() {
        HashSet<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.AI);
        requiredSkills.add(Skill.DB);

        JobOffer jobOffer = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, requiredSkills, true);

        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            jobOffer.setMaxPay(3189.9);
        });
        assertEquals("Invalid pay specified!", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            jobOffer.setMinPay(1613.9);
        });
        assertEquals("Invalid pay specified!", e.getMessage());

        jobOffer.setMaxPay(4020.0);

        e = assertThrows(IllegalArgumentException.class, () -> {
            jobOffer.setMinPay(4021.0);
        });
        assertEquals("Invalid pay specified!", e.getMessage());
    }

    @Test
    void testSkillsGetterNotChangeable() {
        HashSet<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.QM);
        requiredSkills.add(Skill.SE);

        JobOffer jobOffer = new JobOffer(6009490, "Software Test Engineer", "karriere.at", 3190.0, 4000.0, requiredSkills, false);

        HashSet<Skill> requiredSkillsClone = jobOffer.getRequiredSkills();
        requiredSkillsClone.add(Skill.CON);

        assertEquals(false, jobOffer.getRequiredSkills().contains(Skill.CON));
    }

    @Test
    void testEquals() {
        HashSet<Skill> requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.SE);
        requiredSkills.add(Skill.CON);

        JobOffer jobOfferProo = new JobOffer(1234, "PROO Lehrer", "HTL Leonding - Abteilung IF", 3000.0, requiredSkills, false);

        requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.SE);
        requiredSkills.add(Skill.WEB);

        JobOffer jobOfferSew = new JobOffer(1234, "SEW Lehrer", "HTL Leonding - Abteilung ITM", 3001.0, requiredSkills, false);

        requiredSkills = new HashSet<>();
        requiredSkills.add(Skill.DB);
        requiredSkills.add(Skill.AI);

        JobOffer jobOfferDbi = new JobOffer(1235, "DBI Lehrer", "HTL Leonding - Abteilung IF", 3002.0, requiredSkills, false);

        assertEquals(true, jobOfferProo.equals(jobOfferSew));
        assertEquals(true, jobOfferSew.equals(jobOfferProo));
        assertEquals(false, jobOfferDbi.equals(jobOfferProo));
        assertEquals(false, jobOfferProo.equals(jobOfferDbi));
    }

    @Test
    void testComparable() {
        JobOffer premiumHighPaid = new JobOffer(6008029, "Senior Java Software Developer", "Dynatrace Austria GmbH", 3571.0, new HashSet<>(), true);

        assertEquals(true, premiumHighPaid instanceof Comparable<JobOffer>);

        JobOffer premiumMidPaid = new JobOffer(5970433, "Data Scientist - Vehicle Data", "KTM Innovation GmbH", 3190.0, 3190.0, new HashSet<>(), true);
        JobOffer normalHighPaid = new JobOffer(6541997, "Senior Consultant", "Compu-Global-Hyper-Mega-Net", 5000.0, 7000.0, new HashSet<>(), false);
        JobOffer normalMidPaid = new JobOffer(6009490, "Software Test Engineer", "karriere.at", 3200.0, 4000.0, new HashSet<>(), false);
        JobOffer normalLowPaid = new JobOffer(6001974, "Junior Web Developer", "Fancy Agency", 1614.0, 1989.99, new HashSet<>(), false);

        List<JobOffer> offers = new LinkedList<>();
        offers.add(premiumHighPaid);
        offers.add(premiumMidPaid);
        offers.add(normalHighPaid);
        offers.add(normalMidPaid);
        offers.add(normalLowPaid);

        Collections.shuffle(offers);

        Collections.sort(offers);

        assertEquals(premiumHighPaid, offers.get(0));
        assertEquals(premiumMidPaid, offers.get(1));
        assertEquals(normalHighPaid, offers.get(2));
        assertEquals(normalMidPaid, offers.get(3));
        assertEquals(normalLowPaid, offers.get(4));
    }
}