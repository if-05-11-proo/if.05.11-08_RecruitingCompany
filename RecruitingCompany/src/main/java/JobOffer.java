import java.util.HashSet;

public class JobOffer {
    private int mId;
    private String mDescription;
    private String mCompany;
    private double mMinPay;
    private double mMaxPay;
    private HashSet<Skill> mRequiredSkills;
    private boolean mIsPremium;

    public JobOffer(int id, String description, String company, double minPay, HashSet<Skill> requiredSkills, boolean isPremium) {
        throw new RuntimeException(":(");
    }

    public JobOffer(int id, String description, String company, double minPay, double maxPay, HashSet<Skill> requiredSkills, boolean isPremium) {
        throw new RuntimeException(":(");
    }
}
