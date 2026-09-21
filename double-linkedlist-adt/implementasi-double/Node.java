public class Node {

    private String url;
    private Node prev;
    private Node next;

    public Node(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
