## Record with methods.

### 1. Extending the Record Class: Additional Methods

#### Can I add methods to a record?

Of course! A record is like a fully renovated apartment: the walls and floor are already in place, you can't change them, but you can still arrange the furniture however you like. Within a record, you can declare regular methods, static methods, and even store constants. This means that business logic doesn't have to be moved to separate "utility" classes—it can be neatly embedded right within the record itself.

**Example: A method for calculating the distance between points**

Let's say we have a record for a point on a plane:

```java
public record Point(int x, int y) {
    // Additional method
    public double distanceTo(Point other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```

Now you can do this:

```java
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);
    System.out.println(p1.distanceTo(p2)); // 5.0
```

As you can see, the **record** class can be "enriched" with its own methods—and it's very convenient!

**Example: static method**

```java
public record Rectangle(int width, int height) {
    public int area() {
        return width * height;
    }
    
    public static Rectangle square(int size) {
        return new Rectangle(size, size);
    }
}
```

Now you can create a "square" with a single call:

```java
    Rectangle r = Rectangle.square(5);
    System.out.println(r.area()); // 25
```

### 2. Compact Constructor and Data Validation

#### Why do we need a "compact" constructor?

The canonical **record** constructor is created automatically and assigns parameters to fields. But sometimes you want to add input validation (for example, to prohibit negative coordinates).

In a normal class, we would write:

```java
public class Point {
    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        if (x < 0 || y < 0) throw new IllegalArgumentException();
        this.x = x;
        this.y = y;
    }
    // ...
}
```

The **record** class can declare a compact constructor—without repeating the parameter list and without explicitly assigning fields (the compiler does this for us).

**Compact Constructor Syntax**

```java
public record Point(int x, int y) {
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }
        // No need to write: this.x = x; this.y = y;
    }
}
```

- The constructor parameters automatically match the **record** components.
- The assignment **this.x = x** and **this.y = y** is automatically made by the compiler after the constructor body is executed (or after it successfully returns).
- If an exception is thrown, the object will not be created.

**Example with check**

```java
    Point p1 = new Point(3, 5); // OK
    Point p2 = new Point(-1, 2); // Throws IllegalArgumentException!
```

#### Can I declare a "regular" constructor?

Yes, I can! If I need to create a constructor with a different parameter list or additional logic, declare it explicitly:

```java
public record Range(int from, int to) {
    public Range(int size) {
        this(0, size); // calls the primary constructor
    }
}
```

### 3. Limitations of Record Classes

Unlike regular classes, **record** classes have a number of limitations. It's important to remember this to avoid unexpected compilation errors.

#### Components only — no additional non-static fields

The **record** class cannot declare new non-static fields:

```java
public record Person(String name, int age) {
    // int id; // Compilation error! You cannot add non-static fields.
}
```

You can declare static fields and methods:

```java
public record Person(String name, int age) {
    public static final String SPECIES = "Homo sapiens";
}
```

#### Record is always final

The **Record** class cannot be a parent class (you cannot inherit from it) and cannot explicitly inherit from another class (except implicitly from **java.lang.Record**). This means that the **record** class is always a "final" structure.

```java
public record User(String login) {
}
// public class Admin extends User {} // Error: Cannot inherit from record!
```

#### You can implement interfaces

The **Record** class can implement interfaces:

```java
public interface Printable {
    void print();
}

public record Invoice(int amount) implements Printable {
    @Override
    public void print() {
        System.out.println("Amount: " + amount);
    }
}
```

### 4. Examples: Extended Records in Real-World Problems

Let's look at a few practical examples where additional methods and compact constructors make the **record** class truly useful.

#### Record with a Computed Method

```java
public record Circle(double x, double y, double radius) {
    public double area() {
        return Math.PI * radius * radius;
    }
    
    public double distanceTo(Circle other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
```

#### Record with validation

```java
public record Email(String value) {
    public Email {
        if (value == null || !value.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + value);
        }
    }
}
```

Now it is impossible to create an invalid email:

```java
    Email e1 = new Email("test@example.com"); // OK
    Email e2 = new Email("not-an-email"); // Throws IllegalArgumentException
```

#### Record with additional static methods

```java
public record Temperature(double celsius) {
    public static Temperature fromFahrenheit(double fahrenheit) {
        return new Temperature((fahrenheit - 32) * 5 / 9);
    }
    
    public double toFahrenheit() {
        return celsius * 9 / 5 + 32;
    }
}
```

Usage:

```java
    Temperature t = Temperature.fromFahrenheit(98.6);
    System.out.println(t.celsius()); // 37.0
    System.out.println(t.toFahrenheit()); // 98.6
```

### 5. Compact Constructor: Nuances and Limitations

**When to Use a Compact Constructor?**

- When you need to validate data.
- When you need to change values before saving (for example, round a number or convert a string to uppercase).
- When you want to avoid duplicating the parameter list.

**Working Features**

- In a compact constructor, you cannot explicitly assign values to components (**this.x = ...**) — this will cause a compilation error, since the compiler automatically performs the assignment after executing the constructor body.
- In a compact constructor, you cannot change the parameter names — they always match the names of the **record** components.

**Example: Automatic Rounding**

```java
public record Money(double amount) {
    public Money {
        amount = Math.round(amount * 100) / 100.0; // Round to kopecks
    }
}
```

### 6. Practice: Developing a Sample Application

Let's say we're performing banking transactions in a sample application. Let's say we have a **record** class **Transaction** that stores the amount, sender, and recipient.

```java
public record Transaction(String from, String to, double amount) {
    public Transaction {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (from == null || to == null) throw new IllegalArgumentException("Fields cannot be null");
    }
    
    public String description() {
        return String.format("Transfer %.2f from %s to %s", amount, from, to);
    }
}
```

Usage:

```java
    Transaction t = new Transaction("Alice", "Bob", 150.0);
    System.out.println(t.description()); // Transfer 150.00 from Alice to Bob
```

Attempting to create an invalid transaction will result in an error:

```java
    Transaction t2 = new Transaction("Alice", "Bob", -10.0); // IllegalArgumentException
```

#### Table: What is and isn't allowed in a record class

| **What is allowed in a record class** | **What isn't allowed in a record class** |
| --- | --- |
| Regular methods | New non-static fields |
| Static methods and fields | Inherit from other classes |
| Implement interfaces | Be a superclass of others |
| Compact and Regular Constructors | Modifying Components After Creation |
| Overriding Methods | Using Setters |

### 7. Common Mistakes When Working with Record Classes with a Custom Body

**Mistake №1: Attempting to Add a Non-Static Field.**

Beginners often try to add "another field for internal logic" to a record class—for example, a counter or cache. This won't work: the compiler will immediately throw an error. If you need to store additional state, a record class is probably not the right choice.

**Mistake №2: Forgetting Validation in a Compact Constructor.**

If you want an object to always be valid, check it in a compact constructor. Don't rely on the user not entering nonsense.

**Mistake №3: Trying to modify a component after creation.**

The fields of a **record** class are **final** — they cannot be modified either directly or through methods. If you need a mutable structure, use a regular class.

**Mistake №4: Duplicating logic in methods and the constructor.**

Sometimes, people try to duplicate validation and calculation logic in both methods and the constructor. It's better to do all validation in the constructor, and reserve methods for pure business logic.

**Mistake №5: Forgetting about inheritance restrictions.**

A **record** class is always **final** — you cannot create a subclass from it. If you're designing a hierarchy that requires subclasses, use regular classes.
