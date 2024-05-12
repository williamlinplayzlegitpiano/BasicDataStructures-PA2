import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */

    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;
 
    Object[] nullArray = new Object[10]; // {null, null, ..., null}
    Integer[] intArray = { 1, 2, 3 };
    Integer[] size1Array = {1, null, null}; // NOTE: LIST OF SIZE ONE
    Integer[] nullIntegerArray = new Integer[10];

    private MyArrayList listMaxCapacity, listNormalCapacity, listNull, listNullElements;

    @Before
    public void setUp() throws Exception {

        listMaxCapacity = new MyArrayList<>(intArray);
        listNormalCapacity = new MyArrayList<>(size1Array);
        listNull = new MyArrayList<>(null);
        listNullElements = new MyArrayList<>(nullArray);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){

        assertThrows(IllegalArgumentException.class, () -> {
            new MyArrayList(-5);
        });

    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){

        assertArrayEquals("check array details", new Integer[5], listNull.data);

    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){

        for (int i = 0; i < listNullElements.data.length; i++) {
            if (listNullElements.data[i] == null) {
                assertNull(nullArray[i]);
            }
        }

    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listMaxCapacity.append(4);

        assertArrayEquals("check for successful append", new Integer[]{1, 2, 3, 4, null, null}, listMaxCapacity.data);

        assertEquals("check size", 4, listMaxCapacity.size);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listMaxCapacity.append(null);

        assertArrayEquals("check for successful append", new Integer[]{1, 2, 3, null, null, null}, listMaxCapacity.data);

        assertEquals("check size", 4, listMaxCapacity.size);
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        listMaxCapacity.prepend(4);

        assertArrayEquals("check for successful append", new Integer[]{4, 1, 2, 3, null, null}, listMaxCapacity.data);

        assertEquals("check size", 4, listMaxCapacity.size);        
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listMaxCapacity.prepend(null);

        assertArrayEquals("check for successful append", new Integer[]{null, 1, 2, 3, null, null}, listMaxCapacity.data);

        assertEquals("check size", 4, listMaxCapacity.size);    
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBounds(){
       
        int ELEMENT_PLACEHOLDER = 1;

        int NEGATIVE_NUMBER = -1;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.insert(NEGATIVE_NUMBER, ELEMENT_PLACEHOLDER);
        });  

        int ONE_ABOVE_SIZE = 1;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.insert(listMaxCapacity.size + ONE_ABOVE_SIZE, ELEMENT_PLACEHOLDER);
        });

    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){

        int DUMMY_CAPACITY = 2000;
        MyArrayList<Integer> expectedDummy = new MyArrayList<>(DUMMY_CAPACITY);


        for (int i = 0; i < expectedDummy.data.length; i++) {
            expectedDummy.data[i] = i;
            expectedDummy.size++;
        }

        MyArrayList<Integer> actualDummy = new MyArrayList<>(DUMMY_CAPACITY);
        for (int i = 0; i < actualDummy.data.length; i++) {
            actualDummy.insert(i,i);
        }

        assertArrayEquals("content check", expectedDummy.data, actualDummy.data);
        assertEquals("size check", expectedDummy.size, actualDummy.size);
        assertEquals("capacity check", expectedDummy.data.length, actualDummy.data.length);

    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        
        int NEGATIVE_NUMBER = -1;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.get(NEGATIVE_NUMBER);
        });  

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.get(listMaxCapacity.size);
        });        

    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
       
        int NEGATIVE_NUMBER = -1;
        int ELEMENT_PLACEHOLDER = 1;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.set(NEGATIVE_NUMBER, ELEMENT_PLACEHOLDER);
        });  

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.set(listMaxCapacity.size, ELEMENT_PLACEHOLDER);
        });        

    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){

        Integer[] actualDummydata = {1,2,3};
        MyArrayList<Integer> actualDummy = new MyArrayList<>(actualDummydata);

        actualDummy.remove(2);
        actualDummy.remove(1);

        Integer[] expectedDummydata = {1, null, null};
        MyArrayList<Integer> expectedDummy = new MyArrayList<>(expectedDummydata);

        assertArrayEquals("check if both lists are equal", expectedDummy.data, actualDummy.data);

    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        
        int NEGATIVE_NUMBER = -1;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.remove(NEGATIVE_NUMBER);
        });  

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.remove(listMaxCapacity.size);
        });    

    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
        
        assertThrows(IllegalArgumentException.class, () -> {
            listMaxCapacity.expandCapacity(listMaxCapacity.data.length - 1);
        });

    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        
        int PRE_DOUBLE = 2;
        int POST_DOUBLE = 4;

        MyArrayList<Integer> needDoubledList = new MyArrayList<>(PRE_DOUBLE);
        needDoubledList.expandCapacity(POST_DOUBLE);
        assertEquals("test double capacity", 4, needDoubledList.data.length);

        int NULL_ARRAY_CAPACITY = 0;
        int DEFAULT_CAPACITY = 5;

        MyArrayList<Integer> needDefaultList = new MyArrayList<>(NULL_ARRAY_CAPACITY);
        needDefaultList.expandCapacity(DEFAULT_CAPACITY);
        assertEquals("test reset to default capacity", 5, needDefaultList.data.length);

        int VERY_BIG_NUMBER = 50;

        MyArrayList<Integer> doubleNotEnoughList = new MyArrayList<>(PRE_DOUBLE);
        doubleNotEnoughList.expandCapacity(VERY_BIG_NUMBER);
        assertEquals("test set as capacity", 50, doubleNotEnoughList.data.length);

    }

    /**
     * Aims to test the rotate method when 
     * input index is out of bounds
     */
    @Test
    public void testRotateOutOfBound() {
        
        int NEGATIVE_NUMBER = -1;

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.rotate(NEGATIVE_NUMBER);
        });  

        assertThrows(IndexOutOfBoundsException.class, () -> {
            listMaxCapacity.rotate(listMaxCapacity.size);
        });   

    }

    /**
     * Aims to test the find method when 
     * there are multiple of the input element
     */
    @Test
    public void testFindMultiple(){
	    
        assertEquals("check for a non-existing element", 0, listMaxCapacity.find(1));
        assertEquals("check for a non-existing element", 2, listMaxCapacity.find(3));


    }
	
    /**
     * Aims to test the find method when 
     * input element does not exist in the list
     */
    @Test
    public void testFindDoesNotExist(){
        
        assertEquals("check for a non-existing element", -1, listMaxCapacity.find(4));

    }

}
