public interface JobSubject {
    void registerObserver(JobObserver observer);
    void unregisterObserver(JobObserver observer);
}