package facilities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class Maintenance 
{
	public static ArrayList<Maintenance> maintain = new ArrayList<Maintenance>();
	public static ArrayList<Maintenance> maintain_request = new ArrayList<Maintenance>();
	public int schedule_time;
	public boolean request_approval;
	public boolean problem_approval;
	public boolean money_approval;
	public int total_budget;
	public int total_cost;
	public String problems;
	public String maintenance_name;
	public enum status { initiated, in_progress, complete }; //status of a maintenance order options
	public status request_status;
	public LocalDateTime request_initiation_time;
	public LocalDateTime request_completion_time;

	public Maintenance getMaintenanceRequest(String name)
	{
		Maintenance main = null;
		name = name.toLowerCase();
		int check = 0;
		for(int i = 0; i < this.maintain_request.size(); i ++){
            if (this.maintain_request.get(i).getMaintenance_name().toLowerCase().matches(name)){
                main = this.maintain_request.get(i);
                check++;
                break;
            }
        }
        if (check == 0){
            System.out.println("OOPS! Unable to find the maintenance in question.");
        }
       return main;
	}
	public int getSchedule_time()
	{
		return schedule_time;
	}
	public void setSchedule_time(int time)
	{
		this.schedule_time=time;
	}
	public int getTotal_cost()
	{
		return total_cost;
	}
	public void setTotal_cost(int cost)
	{
		this.total_cost=cost;
	}
	public int getTotal_budget()
	{
		return total_budget;
	}
	public void setTotal_budget(int budget)
	{
		this.total_budget=budget;
	}
	public void setRequest_approval(boolean req)
	{
		this.request_approval=req;
	}
	public boolean getRequest_approval()
	{
		return request_approval;
	}
	public void setProblem_approval(boolean prob)
	{
		this.problem_approval=prob;
	}
	public boolean getProblem_approval()
	{
		return problem_approval;
	}
	public void setProblem(String problem)
	{
		this.problems = problem;
	}
	public String getProblem()
	{
		return problems;
	}
	public void setMoney_approval(boolean mon)
	{
		this.money_approval = mon;
	}
	public boolean getMoney_approval()
	{
		return money_approval;
	}
	public void setStatus(status input) { this.request_status = input; }
	public status getStatus(){ return this.request_status; }

	public void makeFacilityRequest(ArrayList<Maintenance> main)
	{
		maintain_request.addAll(main);
	}
	public void addMaintenance(ArrayList<Maintenance> main)
	{
		if (request_approval == true && problem_approval == true)
		{
		maintain.addAll(main);
		System.out.println("Maintenance added");
		}
		else
		{
			System.out.println("Maintenance has not been added");
		}
	}
	public void setMainenanceName(String s)
	{
		this.maintenance_name=s;
	}
	public String getMaintenance_name()
	{
		return maintenance_name;
	}
	public void calcMaintenanceCostForFacility(Maintenance main)
	{
		int cost = main.getTotal_cost();
		int budg = main.getTotal_budget();
		int result = budg - cost;
		if (result <= 0)
		{
			System.out.println("Maintenance will drive us bankrupt");
		}
		else
		{
			System.out.println("Total cost for Maintenance: " + result);
			main.setMoney_approval(true);
		}
	}
	public int calcProblemRateForFacility(int current_capacity, int max_capacity, Maintenance main)
    {
        double problem = 0;
        if (current_capacity == 0)
        {
        	System.out.println("Facility vacated. One moment...");
        	System.out.println("Request approved");
        	main.setProblem_approval(true);
        	return 1;
        }
        else 
        {
        	System.out.println("While the Facility is not vacated, time is being taken away from those individuals");
            problem = (current_capacity / (double) max_capacity) * 100;
            System.out.println("The problem rate is around "+problem+"% for the requested maintenance");
            System.out.println("One moment...");
            if (problem <=70)
            {
                System.out.println("Request approved");
                main.setProblem_approval(true);
                return 1;
            }
            else
            {
                System.out.println("The problem is too high to request maintenance. Please speak to an advisor or VACATE the facility.");
                System.out.println("Request denied");
                return 0;
            }
        }
    }
	public void calcDownTimeForFacility()
	{
		int hour_dif = request_completion_time.getHour() - request_initiation_time.getHour();
		int min_dif = request_completion_time.getMinute() - request_initiation_time.getMinute();
		int sec_dif = request_completion_time.getSecond() - request_initiation_time.getSecond();
		System.out.println("Downtime: "+hour_dif+":"+min_dif+":"+sec_dif);
		
	}
	public void listMaintRequests()
	{
		for (Maintenance main : this.maintain_request)
		{
			System.out.println("Request Information");
			System.out.println("Maintenance name: " + main.maintenance_name);
			System.out.println("Maintenance time: "+main.schedule_time);
			System.out.println("Maintenance Budget: "+ main.total_budget + ". Maintenance Cost: "+ main.total_cost);
			System.out.println("Maintenance Reason: "+ main.problems);
		}
	}
	public void listMaintenance()
	{
		for (Maintenance main : this.maintain)
		{
			System.out.println("Maintenance name: " + main.maintenance_name);
			System.out.println("Maintenance time: "+main.schedule_time);
			System.out.println("Maintenance Budget: "+ main.total_budget + ". Maintenance Cost: "+ main.total_cost);
			System.out.println("Maintenance Reason: " + main.problems);
		}
	}
	public void listFacilityProblems()
	{
		for (Maintenance main : this.maintain)
		{
			System.out.println("Maintenance name: " + main.maintenance_name);
			System.out.println("Problem: "+ main.problems);
		}
	}
	
	public void setMaintenanceRequestBeginTime(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dtf.format(now);
		this.request_initiation_time = now;
	}

	public LocalDateTime getMaintenanceRequestBeginTime(){
		return this.request_initiation_time;
	}

	public void setMaintenanceRequestCompletionTime(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dtf.format(now);
		this.request_completion_time = now;
	}

	public LocalDateTime getMaintenanceRequestCompletionTime(){
		return this.request_completion_time;
	}

}

