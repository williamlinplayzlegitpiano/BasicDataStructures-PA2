public class MyArrayList<E> implements MyList<E> {

    //Instance Variables
    Object[] data; //Underlying data structure
    int size; //number of elements in array

    /**
     * Default Constructor
     */
    public MyArrayList() {
        this.data = new Object[5];
        this.size = 0;
    }

    /**
     * Constructor with an argument of a positive initial capacity
     * @param initialCapacity
     * @throws exception
     */
    public MyArrayList(int initialCapacity) {

        //throws an exception for all non-positive initial capacities
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }

        //the initial capacity would also equal the number of elements in the arraylist
        this.data  = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Constructor with an argument of a non-null array
     * @param arr
     */
    public MyArrayList (E[] arr) {

        //check if the given arraylist has an initial size
        if (arr == null) {
            this.data = new Object[5];
            this.size = 0;
            return;
        }

        //creates a new arraylist that takes the length of the imported arraylist
        this.data = new Object[arr.length];
        
        //imports the values from the old arraylist to the new arraylist 
        by scanning and copying over every index value
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = arr[i];
        }

        //updates the size of the new array to be that of the imported array
        this.size = arr.length;
    }

    /**
     * Method to increase the capacity of the ArrayList. 
     * @param requiredCapacity
     */
    public void expandCapacity(int requiredCapacity) {

        //variable to double the capacity 
        int doubleCapacity = 2;

        /**
         * the if-else loop checks and continues if the requiredCapacity 
         * is longer than the current arraylist length.
         */
        if (requiredCapacity < this.data.length) {
            throw new IllegalArgumentException("requiredCapacity has to be larger than initial capacity");
        } else if (this.data.length == 0) {
            this.data = new Object[5];
        } else if (this.data.length * doubleCapacity < requiredCapacity) {

            //uses a temporary arraylist to expand the arraylist length and uses a for loop to transfer over all the values            
            Object[] tempData = new Object[requiredCapacity];
            for (int i = 0; i < this.size; i++) {
                tempData[i] = this.data[i];
            }
            this.data = tempData;
        } else {

            //uses a temporary arraylist to expand the arraylist length and uses a for loop to transfer over all the values
            Object[] tempData = new Object[this.data.length * doubleCapacity];
            for (int i = 0; i < this.size; i++) {
                tempData[i] = this.data[i];
            }
            this.data = tempData;
        }
    }

    /**
     * Method to return the length of the arraylist
     */
    public int getCapacity() {
        return this.data.length;
    }

    /**
     * Method to insert a value at any index in the arraylist
     * @param index
     * @param element
     * @throws exception
     */
    public void insert(int index, E element) {

        //checks whether or not the given index is within the lengths of the arraylist
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("index needs to be between 0 and array length");
        } else {

            //increases the length of the arraylist if the current capacity is already at its max
            if (this.data.length == this.size) {
                expandCapacity(this.size);
            }

            //shifts right all the elements of the arraylist that is on the right of the given index
            for (int i = this.size; i > index; i--) {
                this.data[i] = this.data[i-1];
            }

            //inserts the given value at the given index location 
            //and incrementing the size because of one more arraylist value
            this.data[index] = element;
            this.size++;
        }   
    }

    /**
     * Method to add a value at the end of the arraylist
     * @param element
     */
    public void append(E element) {
            
        //increases the length of the arraylist if 
        //the current capacity is already at its max
        if (this.data.length == this.size) {
            expandCapacity(this.size);
        }

        //inserts the given value at the end of all the current values, 
        //thus being at the this.size index, consequentially incrementing the size
        this.data[this.size] = element;
        this.size++;
    }

    /**
     * Method to add a value at the beginning of the arraylist
     * @param element
     */
    public void prepend(E element) {

        //increases the length of the arraylist if the 
        //current capacity is already at its max
        if (this.data.length == this.size) {
            expandCapacity(this.size + 1);
        }

        //shifts right all the elements of the arraylist 
        for (int i = this.size - 1; i >= 0; i--) {
            this.data[i+1] = this.data[i];
        }

        //adds the new value at index 0, the beginning of 
        //the arraylist, and incrementing the size
        this.data[0] = element;
        this.size++;
    }

    /**
     * Method to retrieve the value at a specific index
     * @param index
     * @throws exception
     */
    public E get(int index) {

        //exception to check if the given index is within 
        //the available indices of the current arraylist
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) this.data[index];
    }

    /**
     * Method to swap a value at a specific index to another value
     * @param index
     * @param element
     * @throws exception
     */
    public E set(int index, E element) {

        //exception to check if the given index is within 
        //the available indices of the current arraylist
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        
        E storeElement = (E) this.data[index];

        //scans for the specific index and replaces it with the new element
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                this.data[i] = element;
                break;
            }
        }

        //returns the previous, deleted value
        return storeElement;
        
    }

    /**
     * Method to remove a value at any given index
     * @param index
     * @throws exception
     */
    public E remove(int index) {

        //exception to check if the given index is within 
        //the available indices of the current arraylist
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        E removedElement = (E) this.data[index];

        //deletes the value at the specific index
        this.data[index] = null;

        //shifts all values to the left by one to fill in the deleted index
        for (int i = index; i < this.size - 1; i++) {
            this.data[i] = this.data[i+1]; 
        }

        //subtracts size by one due to one element removed
        this.size--;

        return removedElement;

    }

    /**
     * Method to return the number of values in the arraylist
     */
    public int size() {
        return this.size;
    }

    /**
     * Method to rotate the order of the values by number of indices
     * @param index
     * @throws exception
     */
    public void rotate(int index) {

        //exception to check if the given index is within the available indices of the current arraylist
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        //temporary arraylist the store the shifted elements
        Object[] tempData = new Object[this.size];

        //for loop to copy all the values up to the index onto the new arraylist
        for (int i = 0; i < index; i++) {
            tempData[i] = this.data[i];
        }

        //variable to calculate the difference between the last value the previous arraylist and the index
        int replaceIndex = this.size - index;

        /**
         * shifts all non-copied values by "index" amounts all the way to the right,
         * then inserting the temporariliy removed values at the front to create a rotating affect
         */
        for (int i = 0; i < replaceIndex; i++) {
            this.data[i] = this.data[i + index];
        }
        for (int i = replaceIndex; i < this.size; i++) {
            this.data[i] = tempData[i - replaceIndex];
        }
    }

    /**
     * Method to find whether a value exists inside of the arraylist
     * @param element
     */
    public int find(E element) {

        //for loop scans through all of the indices to find a 
        //matching value, then returns the specific index it is first seen at
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}

