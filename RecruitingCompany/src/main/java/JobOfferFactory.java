import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class JobOfferFactory {
    private static final Logger mLogger = LogManager.getLogger(JobOfferFactory.class);

    public static JobOffer createFromString(String offerRaw) {
        //https://www.karriere.at/jobs/6009490;X;Software Test Engineer;karriere.at;2; 3190 ;4000;QM,SE
        //https://www.karriere.at/jobs/5931265;;Agile Software Engineer;MIC Datenverarbeitung GmbH;2;2680.0;;SE,DB,QM
        return null;
    }
}
