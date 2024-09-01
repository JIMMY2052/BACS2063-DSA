/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import boundary.DonorUI;
import entity.Donor;
import entity.Donation;
import adt.SortedListInterface;
import adt.SortedArrayList;
import entity.DonatedItem;
import java.util.Iterator;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;

/**
 *
 * @author User
 */
public class DonorController {

    private DonorUI donorUI = new DonorUI();
    SortedListInterface<Donor> donor = new SortedArrayList<>();
    SortedListInterface<Donation> donation = new SortedArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public DonorController(SortedListInterface<Donor> donor, SortedListInterface<Donation> donation) {
        this.donation = donation;
        this.donor = donor;
    }

    public void addDonor() {
        int exit = 0;
        boolean isSuccess = false;

        do {
            donorUI.addDonorMenu();
            String name = donorUI.inputDonorName();
            if (name.equals("0")) {
                break;
            }
            String contactNo = donorUI.inputDonorContactNo();
            if (contactNo.equals("0")) {
                break;
            }
            String category = donorUI.inputDonorCategory();
            if (category.equals("0")) {
                break;
            }
            String type = donorUI.inputDonorType();
            if (type.equals("0")) {
                break;
            }

            if (donorUI.inputConfirmation("add a new donor")) {
                Donor dr = new Donor(name, contactNo, category, type);
                isSuccess = donor.add(dr);
                
                donorUI.displayDonorDetail();
                System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", dr.getDonorId(), dr.getName(), dr.getContactNo(), dr.getCategory(), dr.getType());
                System.out.println("============================================================================================\n");
                System.out.println("You have successfully added a new donor!!");
            } else {
                System.out.println("You canceled to add a new donor!!");
            }
            
        } while (!isSuccess);

        
    }

    public int searchDonor() {
        int exit, found = 0;
        String id;

        do {
            donorUI.searchDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                return exit = 0;
            }
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                Donor donorObject = getDonor.next();
                if (donorObject.getDonorId().equals(id)) {
                    ++found;
                    System.out.println(donorObject);
                    break;
                }
            }
            if (found == 0) {
                System.out.println("There is no details for this donor.");
            }

            exit = donorUI.inputExitPage();
        } while (exit == 0);

        return exit;
    }

    public int removeDonor() {
        int exit = 0;
        String id;
        boolean isSuccess = false;

        do {
            donorUI.removeDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                return exit;
            }
            if (donorUI.inputConfirmation("remove this donor") == true) {
                Iterator<Donor> getDonor = donor.getIterator();
                while (getDonor.hasNext()) {
                    Donor donorObject = getDonor.next();
                    if (donorObject.getDonorId().equals(id)) {
                        donor.remove(donorObject);
                        isSuccess = true;
                        break;
                    }
                }
                if (isSuccess == true) {
                    System.out.println("Remove the donor successfully.");
                } else {
                    System.out.println("The donor id entered is not in the list.");
                }
            } else {
                System.out.println("Your canceled to remove the donor.");
            }

            exit = donorUI.inputExitPage();
        } while (exit == 0);
        return exit;
    }

    public int updateDonorDetails() {
        int exit, option, count;
        String id, name = "", contactNo = "", category = "", gender = "";
        boolean isSuccess = false;

        do {
            donorUI.updateDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                return exit = 1;
            }
            option = donorUI.updateMenu();
            if (option == 0) {
                return exit = 1;
            }
            switch (option) {
                case 1: {
                    name = donorUI.inputDonorName();
                    if (name.equals("0")) {
                        return exit = 1;
                    }
                    break;
                }
                case 2: {
                    contactNo = donorUI.inputDonorContactNo();
                    if (contactNo.equals("0")) {
                        return exit = 1;
                    }
                    break;
                }
                case 3: {
                    category = donorUI.inputDonorCategory();
                    if (category.equals("0")) {
                        return exit = 1;
                    }
                    break;
                }

            }
            if (donorUI.inputConfirmation("update the donor detail")) {
                Iterator<Donor> getDonor = donor.getIterator();
                count = 0;
                while (getDonor.hasNext()) {
                    count++;
                    Donor donorObject = getDonor.next();
                    if (donorObject.getDonorId().equals(id)) {
                        if (option == 1) {
                            donorObject.setName(name);
                            break;
                        }
                        if (option == 2) {
                            donorObject.setContactNo(contactNo);
                            break;
                        }
                        if (option == 3) {
                            donorObject.setCategory(category);
                            break;
                        }
                    }
                }
            } else {
                System.out.println("You canceled to update the donor details.");
            }

            exit = donorUI.inputExitPage();
        } while (exit == 0);

        return exit;
    }

    public void listDonor() {
        int option;

        do {
            option = donorUI.listMenu();
            switch (option) {
                case 1:
                    listWithDonation();
                    break;
                case 2:
                    filterDonor();
                case 0:
                    break;
            }
        } while (option != 0);
    }

    public void listWithDonation() {
        int exit;
        double tempQuantity = 0.0;
        SortedListInterface<Donation> tempDonation = new SortedArrayList<>();

        if (donor.isEmpty()) {
            System.out.println("Opps!! There is no donor in the list.");
        } else {
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                Donor donorObject = getDonor.next();
                tempDonation = donorObject.getDonorDonationList();
                Iterator<Donation> getDonation = tempDonation.getIterator();
                while (getDonation.hasNext()) {
                    Donation donationObject = getDonation.next();
                    if (donationObject == null) {
                        continue;
                    }
                    System.out.println("Donor Name: " + donationObject.getDonor().getName());
                    if (donationObject.getCategory().equals("F")) {
                        donationObject.getDonor().getName();
                        System.out.println("Food Donation: ");
                        Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
                        while (itDonatedItem.hasNext()) {
                            DonatedItem item = itDonatedItem.next();
                            System.out.println(item.getItemName() + ": " + item.getQuantity());
                        }
                    }
                    if (donationObject.getCategory().equals("C")) {
                        donationObject.getDonor().getName();
                        Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
                        System.out.println("Cash Donation: ");
                        while (itDonatedItem.hasNext()) {
                            DonatedItem item = itDonatedItem.next();
                            System.out.println(item.getItemName() + ": " + item.getQuantity());

                        }

                    }
                }
            }

        }
    }

    public void filterDonor() {
        int exit, option;
        String category, type;
        do {
            donorUI.filterDonorMenu();
            option = donorUI.filterMenu();

            if (donor.isEmpty()) {
                System.out.println("Opps!! There is no donor in the list.");
            } else {
                Iterator<Donor> getDonor = donor.getIterator();
                while (getDonor.hasNext()) {
                    Donor donorObject = getDonor.next();
                    if (option == 1) {
                        System.out.print("Enter the donor category that wanted to list: ");
                        category = scanner.nextLine();
                        SortedListInterface<Donor> filterByCategory = filterByCategory(category);
                        Iterator<Donor> itDonor = filterByCategory.getIterator();
                        while (itDonor.hasNext()) {
                            System.out.println(itDonor.next().toString());
                        }
                        break;
                    }
                    if (option == 2) {
                        System.out.print("Enter the donor gender that wanted to list: ");
                        type = scanner.nextLine();
                        SortedListInterface<Donor> filterByGender = filterByType(type);
                        Iterator<Donor> itDonor = filterByGender.getIterator();
                        while (itDonor.hasNext()) {
                            System.out.println(itDonor.next().toString());
                        }
                        break;
                    }
                }
            }

            exit = donorUI.inputExitPage();
        } while (exit == 0);
    }

    private SortedListInterface<Donor> filterByCategory(String category) {
        SortedListInterface<Donor> result = new SortedArrayList<>();

        Iterator<Donor> itDonor = donor.getIterator();
        while (itDonor.hasNext()) {
            Donor tempDonor = itDonor.next();
            if (tempDonor.getCategory().equals(category)) {
                result.add(tempDonor);
            }
        }
        return result;
    }

    private SortedListInterface<Donor> filterByType(String gender) {
        SortedListInterface<Donor> result = new SortedArrayList<>();

        Iterator<Donor> itDonor = donor.getIterator();
        while (itDonor.hasNext()) {
            Donor tempDonor = itDonor.next();
            if (tempDonor.getType().equals(gender)) {
                result.add(tempDonor);
            }
        }
        return result;
    }

    public void generateReport() {
        int exit;
        int january = 0, february = 0, march = 0, april = 0, may = 0, june = 0, july = 0, august = 0, september = 0, octobor = 0, november = 0, december = 0;
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        do {
            donorUI.reportDonorMenu();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            System.out.println("Generated by: " + formattedDateTime);
            System.out.println("=============================================================================================================================================================\n\n");
            
            if (donor.isEmpty()) {
                System.out.println("Opps!! There is no donor in the list.");
            } else {
                Iterator<Donor> getDonor = donor.getIterator();
                while (getDonor.hasNext()) {
                    Donor donorObject = getDonor.next();
                    if (donorObject.getDate().equals("january")) {
                        january++;
                    } else if (donorObject.getDate().equals("february")) {
                        february++;
                    } else if (donorObject.getDate().equals("march")) {
                        march++;
                    } else if (donorObject.getDate().equals("april")) {
                        april++;
                    } else if (donorObject.getDate().equals("may")) {
                        may++;
                    } else if (donorObject.getDate().equals("june")) {
                        june++;
                    } else if (donorObject.getDate().equals("july")) {
                        july++;
                    } else if (donorObject.getDate().equals("august")) {
                        august++;
                    } else if (donorObject.getDate().equals("september")) {
                        september++;
                    } else if (donorObject.getDate().equals("octobor")) {
                        octobor++;
                    } else if (donorObject.getDate().equals("november")) {
                        november++;
                    } else if (donorObject.getDate().equals("december")) {
                        december++;
                    }
                }
                
                int[] counts = {
                    january, february, march, april, may, june, july, august, september, octobor, november, december
                };
                String[] months = {
                    "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
                };
                
                donorUI.monthlyRegisterMenu(months, counts);
//                Iterator<Donor> getDonor = donor.getIterator();
//                Iterator<Donation> getDonation = donation.getIterator();
//                while (getDonor.hasNext()) {
//                    Donor donorObject = getDonor.next();
//
//                    while (getDonation.hasNext()) {
//                        Donation donationObject = getDonation.next();
//                        if (donationObject.getDonor().getCategory().equals("public")) {
//                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
//                            while (itDonatedItem.hasNext()) {
//                                DonatedItem item = itDonatedItem.next();
//                                if (item.getUnit().equals("KG")) {
//                                    publicTotalKG += item.getQuantity();
//                                } else if (item.getUnit().equals("L")) {
//                                    publicTotalL += item.getQuantity();
//                                } else if (item.getUnit().equals("RM")) {
//                                    publicTotalRM += item.getQuantity();
//                                }
//                            }
//                        }
//                        if (donationObject.getDonor().getCategory().equals("private")) {
//                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
//                            while (itDonatedItem.hasNext()) {
//                                DonatedItem item = itDonatedItem.next();
//                                if (item.getUnit().equals("KG")) {
//                                    privateTotalKG += item.getQuantity();
//                                } else if (item.getUnit().equals("L")) {
//                                    privateTotalL += item.getQuantity();
//                                } else if (item.getUnit().equals("RM")) {
//                                    privateTotalRM += item.getQuantity();
//                                }
//                            }
//                        }
//                        if (donationObject.getDonor().getCategory().equals("government")) {
//                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
//                            while (itDonatedItem.hasNext()) {
//                                DonatedItem item = itDonatedItem.next();
//                                if (item.getUnit().equals("KG")) {
//                                    govermentTotalKG += item.getQuantity();
//                                } else if (item.getUnit().equals("L")) {
//                                    governmentTotalL += item.getQuantity();
//                                } else if (item.getUnit().equals("RM")) {
//                                    governmentTotalRM += item.getQuantity();
//                                }
//                            }
//                        }
//                    }
//                    System.out.println("Public:\n" + "KG: " + publicTotalKG + "\n" + "L: " + publicTotalL + "\n" + "RM: " + publicTotalRM + "\n\n");
//                    System.out.println("Private:\n" + "KG: " + privateTotalKG + "\n" + "L: " + privateTotalL + "\n" + "RM: " + privateTotalRM + "\n\n");
//                    System.out.println("Government:\n" + "KG: " + govermentTotalKG + "\n" + "L: " + governmentTotalL + "\n" + "RM: " + governmentTotalRM + "\n\n");
//                    break;
//                }
            }

            exit = donorUI.inputExitPage();
        } while (exit == 0);
    }

    public void menu() {
        int option;

        do {
            option = donorUI.donorMenu();
            switch (option) {
                case 1:
                    addDonor();
                    break;

                case 2:
                    removeDonor();
                    break;
                case 3:
                    updateDonorDetails();
                    break;
                case 4:
                    searchDonor();
                    break;
                case 5:
                    listDonor();
                    break;
                case 6:
                    generateReport();
                    break;
                case 0:
                    System.out.println("Bye Bye. ^_^");
                    break;
                default:
                    System.out.println("Please enter a valid input!!\n");
                    break;
            }
        } while (option != 0);
    }
}
