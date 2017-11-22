package facilities;
import java.time.LocalDate;
import java.time.LocalTime;

public class Inspection {
    public String inspector_name;
    public LocalDate inspection_date;
    public LocalDate inspection_request_date;
    public LocalTime inspection_time;
    public LocalTime inspection_request_time;
    public String issue;
    public Boolean inspection_passed;

    public Inspection(String name, LocalDate date, LocalTime time, String issue, Boolean success){
        this.inspection_time = time;
        this.inspection_date = date;
        this.inspector_name = name;
        this.inspection_request_date = LocalDate.now();
        this.inspection_request_time = LocalTime.now();
        this.issue = issue;
        this.inspection_passed = success;
    }

    public String getInspection_inspector_name(){ return this.inspector_name; }

    public LocalDate getInspection_date(){ return this.inspection_date; }

    public LocalDate getInspection_request_date(){ return this.inspection_request_date; }

    public LocalTime getInspection_time(){ return this.inspection_time; }

    public LocalTime getInspection_request_time(){ return this.inspection_request_time; }

    public String getInspection_issue(){ return this.issue; }

    public Boolean getInspection_passed() { return this.inspection_passed; }


    public boolean isInUseDuringInterval() {
        if(this.getInspection_date() == LocalDate.now()){
            return true;
        }
        return false;
    }

}
