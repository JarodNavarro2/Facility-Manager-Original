package facilities;
import java.util.*;
//import facilities.Facility_Names_and_Info;

//import facilities.Facility_Names_and_Info;
public class Main
{

	public static void main(String[] args)
	{
		System.out.println("Welcome to Facility Management version 1.5!");
		ArrayList<Facility> facility_list = new ArrayList<Facility>();
		ArrayList<Maintenance> maintain_requests = new ArrayList<Maintenance>();
		ArrayList<Usage> facility_usage = new ArrayList<Usage>();
		ArrayList<Inspection> facility_inspection = new ArrayList<Inspection>();
		introduction();
		Scanner user_intro = new Scanner(System.in);
		String intro = user_intro.next();
		intro=intro.toLowerCase();
		boolean flag= false;
		if (!intro.equals("exit"))
		{
			flag = true;
		}
		while (flag == true)
		{
			outerloop:
			if (intro.equals("1")) //WORKS
			{
				if (facility_list.isEmpty())
				{
					System.out.println("No Facilities have been added!");
					break outerloop;
				}
				else
				{
					//list facilities
					System.out.println("Checking how many facilities have been added.");

					int total_facilities_added = facility_list.size();
					System.out.println(total_facilities_added);
			
					System.out.println("Listing information for each facility in the facility list.");

					for (Facility object : facility_list) {
						System.out.println("Current facility name is: " + object.getFacilityName());
						System.out.println("Current facility capacity is: " + object.getCurrentCapacity());
						System.out.println("Current facility max capacity is: " + object.getMaxCapacity());
						System.out.println("Current facility has " + object.rooms.size() + " room(s).");
						System.out.println("Current facility room names are: ");
						for (Room room : object.rooms){
							String room_name = room.getRoomName();
							System.out.println(room.getRoomName() + " (Rm #: " + room.getRoomNumber() + ")");
						}
					}
				}
			}
			else if (intro.equals("2")) //Add New Facilities - WORKS
			{
				System.out.println("Do you wish to add a facility?");
				@SuppressWarnings("resource")
				Scanner dec = new Scanner (System.in);
				String add = dec.next();
				add = add.toLowerCase();
				boolean check_token = false;
				if (add.startsWith("y")){ check_token = true; }
				else if (add.startsWith("n")){ check_token = false; }
				else { System.out.println("Input did not begin with a 'y' or 'n.' Exiting."); }
				while (check_token)
				{
					Facility fac = new Facility();
					Scanner user_input = new Scanner(System.in);
					System.out.println("Enter the Facility Name");
					String in = user_input.next();
					fac.setFacilityName(in);
					System.out.print("How many rooms would you like to add for the Facility: ");
					int r_input = user_input.nextInt();
					for (int j = 0; j < r_input; j++){
						System.out.println("Creating Room " + (j + 1) + " out of " + r_input + ".");
						System.out.print("Name of room " + (j + 1) + ": ");
						String r_name = user_input.next();
						System.out.print("Maximum capacity of room " + (j + 1) + ": ");
						int r_mc = user_input.nextInt();
						System.out.print("Current capacity of room " + (j + 1) + ": ");
						int r_cc = user_input.nextInt();
						while (r_cc > r_mc){
							System.out.println("Current capacity of room " + (j + 1) + " cannot be more than maximum capacity! Try again.");
							System.out.print("Current capacity of room " + (j + 1) + ": ");
							r_cc = user_input.nextInt();
						}
						System.out.print("Room number of room " + (j + 1) + ": ");
						int r_num = user_input.nextInt();
						boolean successful_room_creation = false;
						if (fac.createARoom(r_name, r_cc, r_mc, r_num)) {
							successful_room_creation = true;
						}
						else {
							while (!successful_room_creation) {
								System.out.println("Unable to create a room, because the desired room # is already assigned.");
								System.out.print("Enter a desired room number: ");
								r_num = user_input.nextInt();
								if (fac.createARoom(r_name, r_cc, r_mc, r_num)) {
									successful_room_creation = true;
								}
							}
						}
					}
					facility_list.add(fac);
					System.out.print("Would you like to add another facility? (yes/no): ");
					String add_check = dec.next();
					add_check = add_check.toLowerCase();
					if (add_check.startsWith("y")) {
						check_token = true;
					} else if (add_check.startsWith("n")) {
						check_token = false;
					}
					else {
						System.out.println("Input did not begin with a 'y' or 'n.' Exiting.");
					}
				}

				break outerloop;
			}
			else if (intro.equals("3")) ////See Facility Usage Rates
			{
				Facility temp_fac= new Facility();
				Usage temp_use = new Usage();
				//TODO: Need to implement Usage after all of usage is complete
				System.out.println("Please choose an option");
				System.out.println("1) List inspections");
				System.out.println("2) List Actual Usage");
				System.out.println("3) Calculate Usage Rates");
				System.out.println("4) Assign a facility to use");
				Scanner user_input = new Scanner (System.in);
				String input = user_input.next();
				if(input.equals("1"))
				{
					temp_fac.getFacilityInspections();
				}
				else if(input.equals("2"))
				{
					System.out.print("What is the name of the facility?");
					Scanner fac_in = new Scanner (System.in);
					String fac_out = fac_in.next();
					temp_fac = getFacility(fac_out,facility_list);
					temp_use = temp_fac.getFacilityUsage();
					temp_use.listActualUsage(temp_fac);
				}
				else if(input.equals("3"))
				{
					System.out.print("What is the name of the facility?");
					Scanner fac_in = new Scanner (System.in);
					String fac_out = fac_in.next();
					temp_fac = getFacility(fac_out,facility_list);
					temp_use = temp_fac.getFacilityUsage();
					temp_use.calcUsageRate(temp_fac);
				
				}
				else if(input.equals("4"))
				{
					System.out.print("What is the name of the facility?");
					Scanner fac_in = new Scanner (System.in);
					String fac_out = fac_in.next();
					temp_fac = getFacility(fac_out,facility_list);
					temp_use = temp_fac.getFacilityUsage();
					System.out.println("What type of Usage would you like to assign? (Buisness/Residential/Commercial/Evicted/Vacant)");
					String use_out = fac_in.next().toLowerCase();
					if(temp_fac.getOccupancy() && !fac_out.equals("vacant")){
						temp_use.setUse(use_out);
					}
					else{
						System.out.println("Can't assign Vacant to a full capacity");
						System.out.println("What type of Usage would you like to assign? (Buisness/Residential/Commercial/Evicted/Vacant)");
					}
					temp_use.setUse(use_out);

					System.out.println(temp_use.getUse());
				}
				else
				{
					System.out.println("Error");
					break outerloop;
				}
				break outerloop;
			}
			else if (intro.equals("4")) ////Request Maintenance
			{
				if (facility_list.isEmpty())
				{
					System.out.println("No facilites have been made. Therefore no maintenance can be made");
					break outerloop;
				}
				else
				{
					System.out.println("Would you like to schedule maintenance?");
					Scanner ma_input = new Scanner (System.in);
					String ma = ma_input.next();
					ma = ma.toLowerCase();
					boolean check = false;
					if (!ma.equals("no") || !(ma.equals("n")))
					{
						check=true;
					}
					while (check) //while user has selected yes
					{
					//TODO: implement a start timestamp for when request is first made.
						Maintenance request = new Maintenance();
						Facility req = new Facility();
						System.out.print("Enter the Maintenance name: ");
						Scanner ma_add = new Scanner(System.in);
						String _add = ma_add.next();
						request.setMainenanceName(_add);
						System.out.print("Enter the time for " + _add + ": ");
						int time = ma_add.nextInt();
						request.setSchedule_time(time);
						System.out.print("Enter the total budget for " + _add + ": ");
						int total_budget = ma_add.nextInt();
						request.setTotal_budget(total_budget);
						System.out.print("Enter the total cost for " + _add + ": ");
						int total_cost = ma_add.nextInt();
						request.setTotal_cost(total_cost);
						System.out.println("Enter the problem regarding this request");
						String prob = ma_add.next();
						request.setProblem(prob);
						System.out.print("Enter the name for the Facility this request goes to: ");
						String name=ma_add.next();
						req = getFacility(name, facility_list);
						request.calcProblemRateForFacility(req.getCurrentCapacity(), req.getMaxCapacity(), request);
						if (request.problem_approval==true)
						{
							System.out.println("Calculating funds");
							request.calcMaintenanceCostForFacility(request);
							if (request.money_approval == true)
							{							
								System.out.println("Request added. Listing information.");
								maintain_requests.add(request);
								request.makeFacilityRequest(maintain_requests);
								for (Maintenance main : maintain_requests) 
								{
									System.out.println("Request name: " + main.getMaintenance_name());
									System.out.println("Request time: " + main.getSchedule_time());
									System.out.println("Request budget: " + main.getTotal_budget());
									System.out.println("Request cost: " + main.getTotal_cost());
									System.out.println("Problem: "+ main.getProblem());
									System.out.println("Facility Name: "+name);
									request.setMaintenanceRequestBeginTime(); //will finish tomorrow
								}
							}
						}
						else
						{
							System.out.println("Request denied");
						}
						System.out.println("Add another request?");
						ma = ma_input.next();
						if (ma.equals("no")) {
							System.out.println("Request has been added.");
							check = false;
					}
				}
				break outerloop;
				}
			}
			else if (intro.equals("5"))
			{
				if (maintain_requests.isEmpty())
				{
					System.out.println("No requests at this time");
					break outerloop;
				}
				Maintenance temp = new Maintenance();
				System.out.println("Listing requests");
				int counter = 1;
				for (Maintenance main : maintain_requests) {
					System.out.println("Request #" + counter + " information.");
					System.out.println("Request name: " + main.getMaintenance_name());
					System.out.println("Request scheduled time: " + main.getSchedule_time());
					System.out.println("Request budget: " + main.getTotal_budget());
					System.out.println("Problem: "+ main.getProblem());
					System.out.println("Request cost: " + main.getTotal_cost());
					counter++;
				}
				System.out.println("Please enter the name of the request to approve");
				Scanner name = new Scanner(System.in);
				String answer = name.next();
				if (!answer.isEmpty())
				{
				temp = temp.getMaintenanceRequest(answer);
				System.out.println("Request name: "+ temp.getMaintenanceRequest(answer).getMaintenance_name());
				System.out.println("Request scheduled time: "+ temp.getMaintenanceRequest(answer).getSchedule_time());
				System.out.println("Request budget: "+ temp.getMaintenanceRequest(answer).getTotal_budget());
				System.out.println("Request cost: "+ temp.getMaintenanceRequest(answer).getTotal_cost());
				System.out.println("Problem: "+ temp.getMaintenanceRequest(answer).getProblem());
				}
				System.out.println("Approve request?");
				Scanner approval = new Scanner(System.in);
				String approve = approval.next();
				approve = approve.toLowerCase();
				if (approve.startsWith("y"))
				{
					
					temp.getMaintenanceRequest(answer).setRequest_approval(true);
					temp.getMaintenanceRequest(answer).addMaintenance(maintain_requests);
					temp.getMaintenanceRequest(answer).setMaintenanceRequestCompletionTime();
					temp.calcDownTimeForFacility();
					break outerloop;

				}
				else if (approve.startsWith("n"))
				{
					break outerloop;
				}
				else
				{
					System.out.println("Error");
					break outerloop;
				}
			}
			else if (intro.equals("6")){ //Create an Inspection
				if (facility_list.isEmpty())
				{
					System.out.println("No facilities have been made. Therefore no inspections can be logged.");
					break outerloop;
				}
				System.out.print("Would you like to log an inspection? (yes/no): ");
				Scanner il_input = new Scanner (System.in);
				String il = il_input.next();
				il = il.toLowerCase();
				boolean check = false;
				if (il.startsWith("y"))
				{
					check=true;
				}
				else { System.out.print("Answer did not begin with a 'y' or 'n.'"); }
				while (check) { //while user has selected yes
					Facility tempFacility = new Facility();
					System.out.print("Enter the facility name for which you would like to log an inspection: ");
					String il_facility_name = il_input.next();
					il_facility_name = il_facility_name.toLowerCase();
					tempFacility  = getFacility(il_facility_name, facility_list);
					tempFacility.createInspection();
					System.out.print("Would you like to log another inspection? (yes/no): ");
					String il2 = il_input.next();
					il2 = il2.toLowerCase();
					if (il2.startsWith("n"))
					{
						check=false;
					}
					else {
						System.out.println("Answer did not begin with a 'y' or 'n.' Exiting.");
						check = false;
					}
				}
				break outerloop;
			}
			else if (intro.equals("7")){ //List all inspections for a facility
				if (facility_list.isEmpty())
				{
					System.out.println("No facilities have been made. Therefore no inspections have been logged.");
					break outerloop;
				}
				Scanner ilo_input = new Scanner (System.in);
				System.out.print("Enter the facility name for which you would like to see the inspection log: ");
				String ilo_facility_name = ilo_input.next();
				ilo_facility_name = ilo_facility_name.toLowerCase();
				Facility temp_facility = getFacility(ilo_facility_name, facility_list);
				if (temp_facility != null){
					temp_facility.getFacilityInspections();
				}
				break outerloop;
			}
			else if (intro.equals("8")){
				if (facility_list.isEmpty())
				{
					System.out.println("No facilities exist, therefore unable to change room information for any facility.");
					break outerloop;
				}
				Scanner cri = new Scanner (System.in);
				System.out.print("Enter the facility name for which you would like to change room info for: ");
				String cri_fac = cri.next();
				cri_fac=cri_fac.toLowerCase();
				int check = 0;
				Facility temp_facility = getFacility(cri_fac, facility_list);
				if (temp_facility != null){
					System.out.print("Enter the room # for which you'd like to change info for: ");
					int cri_rn = cri.nextInt();
					if (temp_facility.changeRoomInfo(cri_rn)){
						check++;
					}
					else {
						System.out.println("Something went wrong. Unable to change the room information.");
					}
				}
				else {
					System.out.println("Unable to continue.");
					break outerloop;
				}
				break outerloop;
			}
			else if (intro.equals("9")){ //Delete a Room
				if (facility_list.isEmpty())
				{
					System.out.println("No facilities exist, therefore no rooms exist to be deleted.");
					break outerloop;
				}
				Scanner deleteRoom = new Scanner (System.in);
				System.out.print("Enter the facility name in which you would like to delete a room in: ");
				String deleteRoomFacility = deleteRoom.next();
				deleteRoomFacility=deleteRoomFacility.toLowerCase();
				Facility tempFacility = new Facility();
				tempFacility  = getFacility(deleteRoomFacility, facility_list);
				if (tempFacility != null){
					System.out.print("Enter the room # for which you'd like to change info for: ");
					int deleteRoomNum = deleteRoom.nextInt();
					if(tempFacility.bulldozeARoom(deleteRoomNum)){System.out.println("Success.");}
					else {System.out.println("Could not find the room");}
				}
				break outerloop;
			}
			else if (intro.equals("10"))
			{
				if (facility_list.isEmpty())
				{
					System.out.println("No facilities exist, therefore unable to enforce any evacuation.");
					break outerloop;
				}
				Scanner vacateRoom= new Scanner (System.in);
				System.out.println("Enter the facility name in which you would like to vacate");
				String vacateRoomFacility = vacateRoom.next();
				vacateRoomFacility=vacateRoomFacility.toLowerCase();
				Usage tempUsage = new Usage();
				Facility tempFac = new Facility();
				tempFac = getFacility(vacateRoomFacility,facility_list);
				tempUsage.vacateFacility(vacateRoomFacility, facility_list);
				tempFac = null;

			}
			else if (intro.equals("exit"))
			{
				System.out.println("Logging out");
				flag = false;
				break;
			}
			else
			{
				System.out.println("Error");
				break outerloop;
			}
			introduction();
			intro = user_intro.next();
		}

	}
	public static void introduction()
	{
		System.out.println("Please choose your option regarding you current concerns");
		System.out.println("1) List Current Facilities"); //WORKING 02.15 - DARYA
		System.out.println("2) Add New Facilities"); //WORKING 02.15 - DARYA
		System.out.println("3) See Facility Usage Rates"); // Working 2/20 - Carl
		System.out.println("4) Request Maintenance");//Working 02.18- Jarod
		System.out.println("5) Approve Maintenance");//Working 2.18- Jarod
		System.out.println("6) Create an Inspection"); //WORKING 02.19 - DARYA
		System.out.println("7) List all inspections for a facility"); //WORKING 02.19 - DARYA
		System.out.println("8) Change Room Information"); //WORKING 02.15 - DARYA
		System.out.println("9) Delete a Room"); //WORKING 02.19 - DARYA
		System.out.println("10) Evacuate a Facility"); //WORKING 02.19 - DARYA
		System.out.println("Type 'Exit' to leave the program");
	}

	public static Facility getFacility(String name, ArrayList<Facility> facList){
		Facility fac = null;
		name=name.toLowerCase();
		int check = 0;
		for(int i = 0; i < facList.size(); i ++){
			if (facList.get(i).getFacilityName().toLowerCase().matches(name)){
				fac = facList.get(i);
				check++;
				break;
			}
		}
		if (check == 0){
			System.out.println("OOPS! Unable to find the facility in question.");
		}
		return fac;
	}


}