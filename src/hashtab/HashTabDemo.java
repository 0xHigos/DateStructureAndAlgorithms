package hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(8);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员\t\tlist:显示雇员\t\tfind:查找雇员\t\tdelete:删除雇员\t\texit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("请输入一个数：");
                    int id = scanner.nextInt();
                    System.out.print("请输入名字：");
                    String name = scanner.next();
                    hashTab.add(new Emp(id, name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.print("请输入id名用于查找雇员:");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.print("请输入id名用于删除雇员:");
                    id = scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTab {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        empLinkedLists[hashfun(emp.id)].add(emp);
    }

    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void findEmpById(int id) {
        Emp curEmp = empLinkedLists[hashfun(id)].findEmpById(id);
        if (curEmp == null)
            System.out.println("没有找到该雇员~~~");
        else
            System.out.println("在第" + (hashfun(id) + 1) + "条链表中找到了雇员" + curEmp.toString());
    }

    public void deleteEmpById(int id) {
        boolean deleteEmpById = empLinkedLists[hashfun(id)].deleteEmpById(id);
        if(deleteEmpById)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
    }

    private int hashfun(int id) {
        return id % size;
    }
}

class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        } else {
            Emp curEmp = head.next;
            while (head.next != null) {
                curEmp = curEmp.next;
            }
            curEmp.next = emp;
        }
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第 " + (no + 1) + " 条链表为空~~~");
        } else {
            Emp curEmp = head;
            while (curEmp != null) {
                System.out.printf("第 " + (no + 1) + " 条链表为内容 =>id=%d name=%s\t\n", curEmp.id, curEmp.name);
                curEmp = curEmp.next;
            }
        }
    }

    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        } else if (head.id == id) {
            return head;
        } else {
            Emp curEmp = head.next;
            while (curEmp != null && curEmp.id != id) {
                curEmp = curEmp.next;
            }
            if (curEmp != null)
                return curEmp;
        }
        return null;
    }

    public boolean deleteEmpById(int id) {
        if (head == null) {
            return false;
        } else if (head.id == id) {
            head = head.next;
            return true;
        } else {
            Emp curEmp = head.next;
            while (curEmp != null && curEmp.id != id) {
                curEmp = curEmp.next;
            }
            if (curEmp != null) {
                curEmp = curEmp.next;
                return true;
            }
        }
        return false;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
