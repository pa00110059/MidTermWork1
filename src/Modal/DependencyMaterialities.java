package Modal;

public class DependencyMaterialities {
    private String Process;
    private String Ecosystem_Service;
    private String Rating;
    private String Justification;

    public DependencyMaterialities() {
    }

    public DependencyMaterialities(String process, String ecosystem_Service, String rating, String justification) {
        Process = process;
        Ecosystem_Service = ecosystem_Service;
        Rating = rating;
        Justification = justification;
    }

    public String getProcess() {
        return Process;
    }

    public void setProcess(String process) {
        Process = process;
    }

    public String getEcosystem_Service() {
        return Ecosystem_Service;
    }

    public void setEcosystem_Service(String ecosystem_Service) {
        Ecosystem_Service = ecosystem_Service;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getJustification() {
        return Justification;
    }

    public void setJustification(String justification) {
        Justification = justification;
    }

    @Override
    public String toString() {
        return "DependencyMaterialities{" +
                "Process='" + Process + '\'' +
                ", Ecosystem_Service='" + Ecosystem_Service + '\'' +
                ", Rating='" + Rating + '\'' +
                ", Justification='" + Justification + '\'' +
                '}';
    }
}
