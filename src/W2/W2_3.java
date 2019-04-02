package W2;

public class W2_3{
    public static void main(String[] args) {
        SalariedEmployee s=new SalariedEmployee("lalala", "1234567", 13400);
        HourlyEmployee h=new HourlyEmployee("ninini", "2345678", 90, 100);
        CommissionEmployee c=new CommissionEmployee("dididi", "3456789", 0.15, 100);

        s.print();
        s.setName("lala");
        System.out.println("改名后:"+s.getName());
        s.setId("0123456");
        System.out.println("改ID后:"+s.getId());
        s.setms(10000);
        System.out.println("改月薪后:"+s.getms());
        System.out.println();

        h.print();
        h.seths(100);
        System.out.println("改时薪后:"+h.geths());
        h.setwn(90);
        System.out.println("改工作数后:"+h.getwn());
        System.out.println();

        c.print();
        c.setp(0.2);
        System.out.println("改提成后:"+c.getp());
        c.sett(1000);
        System.out.println("改销售额后:"+c.gett());
    }
}

class Employee{
    private String name,id;
    public Employee(String name,String id)
    {
        this.name=name;
        this.id=id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setId(String id)
    {
        this.id=id;
    }
    public String getName()
    {
        return name;
    }
    public String getId()
    {
        return id;
    }
    public void print()
    {
        System.out.println("姓名:"+name);
        System.out.println("身份证号:"+id);
    }
}

class SalariedEmployee extends Employee
{
    private int ms;
    public SalariedEmployee(String name,String id,int ms)
    {
        super(name,id);
        this.ms=ms;
    }

    public void setms(int ms)
    {
        this.ms=ms;
    }
    public int getms()
    {
        return ms;
    }

    public void print()
    {
        super.print();
        System.out.println("月薪:"+ms);
    }
}

class HourlyEmployee extends Employee
{
    private int hs,wn;
    public HourlyEmployee(String name ,String id,int hs,int wn)
    {
        super(name,id);
        this.hs=hs;
        this.wn=wn;
    }
    public void seths(int hs)
    {
        this.hs=hs;
    }
    public void setwn(int wn)
    {
        this.wn=wn;
    }
    public int geths()
    {
        return hs;
    }
    public int getwn()
    {
        return wn;
    }
    public void print()
    {
        super.print();
        System.out.println("时薪:"+hs);
        System.out.println("工作数:"+wn);
    }
}

class CommissionEmployee extends Employee
{
    private double p;
    private int t;
    public CommissionEmployee(String name,String id,double p,int t)
    {
        super(name,id);
        this.p=p;
        this.t=t;
    }
    public void setp(double p)
    {
        this.p=p;
    }
    public void sett(int t)
    {
        this.t=t;
    }
    public double getp()
    {
        return p;
    }
    public int gett()
    {
        return t;
    }
    public void print()
    {
        super.print();
        System.out.println("提成比例:"+p);
        System.out.println("销售总额:"+t);
    }

}

