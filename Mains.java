import java.util.Scanner;

class Units {
    private int unit;

    public int getUnit() {
        return this.unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void readUnit() {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the unit :");
        int unitInput = sc.nextInt();
        setUnit(unitInput);
    }

}

class Charge extends Units {
    void diplay() {
        System.out.println("1: for the shop  ");
        System.out.println("2: form the home ");
        System.out.println("3:for the school ");
        System.out.println("4: for the temple ");
        System.out.println("for the factory");
    }

    int checkUnitCharge() {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        readUnit();

        int unitCharge;

        switch (choice) {
            case 1:
                unitCharge = getUnit() * 200;
                break;
            case 2:
                unitCharge = getUnit() * 800;
                break;
            case 3:
                unitCharge = getUnit() * 1000;
                break;
            case 4:
                unitCharge = getUnit() * 1500;
                break;
            default:
                System.out.println("Enter the correct option : ");
                unitCharge = 0;
        }

        return unitCharge;
    }
}

interface Electricity {
    void totalBill();
}

interface Tax {
    double calculateTax();
}

class ElectricityBill extends Charge implements Electricity, Tax {
    ElectricityBill() {
    }

    int total = 0;

    @Override
    public void totalBill() {
        total = checkUnitCharge();//+500;
        System.out.println("The total amount is : " + total);
    }

    @Override
    public double calculateTax() {
        if (total <= 8000) {
            return (total * 12.97);
        } else if (total <= 48000) {
            return (total * 24.88);
        } else if (total <= 80000) {
            return (total * 45.73);
        }
        return (total * 58.71);
    }
}

class Advance extends ElectricityBill {
    private double advancePaid;
    private String nic, billNo;

    Advance() {
    }

    Advance(String billNo, String nic, double advancePaid) {
        this.advancePaid = advancePaid;
        this.nic = nic;
        this.billNo = billNo;
    }

    /**
     * creating getter and setter for advance class private members
     **/

    public double getAdvancePaid() {
        return advancePaid;
    }

    public void setAdvancePaid(double advancePaid) {
        this.advancePaid = advancePaid;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    // Confirmation for calculate the bill
    String check() {
        Scanner sc = new Scanner(System.in);
        System.out.println("please again enter the nic number for confirmation");
        nic = sc.next();
        return nic;
    }
}

//--------------------Main method-----------------------
class Mains {
    public static void main(String args[]) {
       // String nics;
        System.out.println("\n-------- *****Electricity Bill*****-------- \n");

        Scanner sc = new Scanner(System.in);
        Advance ad = new Advance();
        System.out.print("Bill Number : ");
        ad.setBillNo(sc.next());
        System.out.print("NIC Number  : ");
        ad.setNic(sc.next());
        System.out.print("Advance Payment   : ");
        ad.setAdvancePaid(sc.nextDouble());


        Advance advanceList[] = new Advance[]{
                new Advance(ad.getBillNo(), ad.getNic(), ad.getAdvancePaid())
        };


       // nics = ad.getNic();
        if (ad.check().contains(ad.getNic())) {
            Charge charge = new Charge();
            charge.diplay();
            // charge.checkunitcharge();

            ElectricityBill elc = new ElectricityBill();
            elc.totalBill();
            //total payment after subtract the advance balance----------------------
            System.out.println("This Month Total Payment with Tax : " + (elc.calculateTax() - ad.getAdvancePaid()));
            //System.out.print("FINAL PAYMENT : "+ad.adpay());

        } 
        else {
            System.out.println("enter the valid nic number");
        }


    }
}
