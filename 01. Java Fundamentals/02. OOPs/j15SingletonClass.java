public class j15SingletonClass {
    public static void main(String args[])
    {
        Book b1 = Book.get_book_instant(200,"Game of Thrones");// new Book() cannot calll as it is pribate 

        System.out.println(b1.name);

        Book b2 = Book.get_book_instant(201,"Song of Ice and Fire");

        System.out.println(b2.name); // only first object is created;

        System.out.println(b1 == b2); // will print true as both are pointing to same object
    }
    
}

class Book
{
    int pages;
    String name;

    private Book(int pages,String name)
    {
        this.pages = pages;
        this.name = name;
    }

    private static Book inst;

    public static Book get_book_instant(int pages,String name)
    {
        if(inst == null)
        {
            inst = new Book(pages,name);
        }
        return inst;
    }
}
