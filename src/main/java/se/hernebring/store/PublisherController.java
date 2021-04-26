/**
 * 
 */
package se.hernebring.store;

import se.hernebring.domain.Publisher;

/**
 * @author HP
 *
 */
public class PublisherController implements Controller {

    private Publisher publisher;
    private int id;

    public PublisherController() {
        create("Addison-Wesley Professional");
    }
    
    public void create(String name) {
        publisher = new Publisher(name);
    }

    public void save(String name) {
        create(name);
        save();
    }
    
    @Override
    public void save() {
        Persistence.save(publisher);
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isNull() {
        return publisher == null ? true : false;
    }

    public void getFirstIdFromStorage() {
        publisher = (Publisher) Persistence.getPublisher(1);
        this.id = 1;
    }
    
    public Publisher getId(int id) {
        this.id = id;
        publisher = (Publisher) Persistence.getPublisher(id);
        return publisher;
    }

    public void connect(AuthorController authorController) {
        Persistence.connect(1);
    }

    public boolean isConnected(AuthorController authorController) {
        return Persistence.isConnected(1);
    }

}
