package ua.edu.sumdu.j2se.Lytovka.tasks;

public class LinkedTaskListNode {
    private Task data = null;
    private LinkedTaskListNode next = null;

    public LinkedTaskListNode() {
    }


    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }

    public LinkedTaskListNode getNext() {
        return next;
    }

    public void setNext(LinkedTaskListNode next) {
        this.next = next;
    }
}
