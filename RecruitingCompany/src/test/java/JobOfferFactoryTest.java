import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class JobOfferFactoryTest {
    @Test
    void testCreatingValidOffers() {
        JobOffer jobOffer = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5932948;X;Director Software Development; Dynatrace Austria GmbH;5;5714.0;;SE,PM");

        assertEquals(5932948, jobOffer.getId());
        assertEquals("Director Software Development", jobOffer.getDescription());
        assertEquals("Dynatrace Austria GmbH", jobOffer.getCompany());
        assertEquals(5714.0, jobOffer.getMinPay(), 0.001);
        assertEquals(8571.0, jobOffer.getMaxPay(), 0.001);
        assertEquals(true, jobOffer.getRequiredSkills().containsAll(Arrays.asList(Skill.SE, Skill.PM)));
        assertEquals(2, jobOffer.getRequiredSkills().size());
        assertEquals(true, jobOffer.isPremium());

        jobOffer = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5988424;;   Angular Frontend Developer; epunkt GmbH  ;2;2700.5;4499.99;WEB,DB");

        assertEquals(5988424, jobOffer.getId());
        assertEquals("Angular Frontend Developer", jobOffer.getDescription());
        assertEquals("epunkt GmbH", jobOffer.getCompany());
        assertEquals(2700.5, jobOffer.getMinPay(), 0.001);
        assertEquals(4499.99, jobOffer.getMaxPay(), 0.001);
        assertEquals(true, jobOffer.getRequiredSkills().containsAll(Arrays.asList(Skill.WEB, Skill.DB)));
        assertEquals(2, jobOffer.getRequiredSkills().size());
        assertEquals(false, jobOffer.isPremium());

        jobOffer = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5950141;;IT System Engineering ;voestalpine AG;3;3501.0;;IT");

        assertEquals(5950141, jobOffer.getId());
        assertEquals("IT System Engineering", jobOffer.getDescription());
        assertEquals("voestalpine AG", jobOffer.getCompany());
        assertEquals(3501.0, jobOffer.getMinPay(), 0.001);
        assertEquals(5251.5, jobOffer.getMaxPay(), 0.001);
        assertEquals(true, jobOffer.getRequiredSkills().containsAll(Arrays.asList(Skill.IT)));
        assertEquals(1, jobOffer.getRequiredSkills().size());
        assertEquals(false, jobOffer.isPremium());

        jobOffer = JobOfferFactory.createFromString("https://www.karriere.at/jobs/6009490;X;Software Test Engineer;karriere.at;2; 3190.0 ;4000.0;QM,SE");

        assertEquals(6009490, jobOffer.getId());
        assertEquals("Software Test Engineer", jobOffer.getDescription());
        assertEquals("karriere.at", jobOffer.getCompany());
        assertEquals(3190.0, jobOffer.getMinPay(), 0.001);
        assertEquals(4000.0, jobOffer.getMaxPay(), 0.001);
        assertEquals(true, jobOffer.getRequiredSkills().containsAll(Arrays.asList(Skill.SE, Skill.QM)));
        assertEquals(2, jobOffer.getRequiredSkills().size());
        assertEquals(true, jobOffer.isPremium());
    }

    @Test
    void testCreatingInvalidOffers() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            JobOffer jobOffer = JobOfferFactory.createFromString("https://www.karriere.at/jobs/5932948;X;Director Software Development; Dynatrace Austria GmbH;5;5714.0;5713.0;SE,PM");
        });
        assertEquals("Invalid pay specified!", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> {
            JobOffer jobOffer = JobOfferFactory.createFromString("https://www.karriere.at/jobs/19741997;;Junior Web Developer; Fancy Agency;2;1600.0;1900.0;WEB,DB");
        });
        assertEquals("Invalid pay specified!", e.getMessage());
    }
}