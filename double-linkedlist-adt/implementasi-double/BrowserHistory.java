public class BrowserHistory {

    private Node head;
    private Node current;
    private int size;

    public BrowserHistory(String homepage) {
        if (homepage == null || homepage.isBlank()) {
            throw new IllegalArgumentException("Homepage tidak boleh kosong.");
        }
        head = new Node(homepage);
        current = head;
        size = 1;
    }

    public void visit(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("URL tidak boleh kosong.");
        }
        Node newNode = new Node(url);
        current.setNext(newNode);
        newNode.setPrev(current);
        current = newNode;
        size++;
    }

    public boolean canGoBack() {
        return current.getPrev() != null;
    }

    public boolean canGoForward() {
        return current.getNext() != null;
    }

    public void back() {
        if (!canGoBack()) {
            throw new IllegalStateException("Sudah di halaman paling awal.");
        }
        current = current.getPrev();
    }

    public void forward() {
        if (!canGoForward()) {
            throw new IllegalStateException("Sudah di halaman paling akhir.");
        }
        current = current.getNext();
    }

    public String getCurrentUrl() {
        return current.getUrl();
    }

    public Node getHead() {
        return head;
    }

    public Node getCurrentNode() {
        return current;
    }

    public int size() {
        return size;
    }
}
