package ua.edu.sumdu.j2se.Lytovka.tasks;

public class LinkedTaskListNode1 {
    private Task data = null;
    private LinkedTaskListNode1 next = null;

    public LinkedTaskListNode1() {
    }


    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }

    public LinkedTaskListNode1 getNext() {
        return next;
    }

    public void setNext(LinkedTaskListNode1 next) {
        this.next = next;
    }
}
