# CMSC 315 Project #3: Binary Trees

In this project, you'll work with various types of Binary Trees, including
Complete Binary Trees, Binary Heaps, and Binary Search Trees.

Let's review the similarities and differences between these tree structures.

---

### 1. **Binary Tree (General)**

A binary tree is a tree in which each node has at most two children. There are
no additional constraints.

#### Example:

```
       A
      / \
     B   C
        /
       D
```

- Node A has two children (B and C).
- Node C has one child (D).
- No rules about ordering or completeness are followed.

---

### 2. **Complete Binary Tree**

A complete binary tree is a binary tree in which every level, except possibly
the last, is completely filled, and all nodes in the last level are as far left
as possible — meaning the last level is filled left to right with no gaps.

#### Example:

```
       A
      / \
     B   C
    / \
   D   E
```

- All levels are filled except the last.
- The last level is filled **left to right**.

---

### 3. **Binary Heap**

A binary heap is a complete binary tree where each parent node follows the
**heap property**:

- **Max-heap**: Parent ≥ children.
- **Min-heap**: Parent ≤ children.

#### Example (Max-Heap):

```
       90
      /  \
    70    50
   / \   /
  20 40 10
```

- Every parent is **greater than or equal to** its children.
- It is also a **complete binary tree**.

---

### 4. **Binary Search Tree (BST)**

A binary search tree (BST) is a binary tree where, for every node, all values in
its left subtree are less than the node's value, and all values in its right
subtree are greater. This rule must hold recursively for every node in the tree,
not just its direct children.

#### Example:

```
         60
        /  \
      40    80
     /        \
   30          90
     \        /  \
     35     85   100
                   \
                   102
```

---

### Summary Table:

| Tree Type            | Structure                           | Ordering Property     |
| -------------------- | ----------------------------------- | --------------------- |
| Binary Tree          | Any                                 | None                  |
| Complete Binary Tree | All levels full, last left-to-right | None                  |
| Binary Heap (Max)    | Complete                            | Parent ≥ children     |
| Binary Search Tree   | Any                                 | Left < Parent < Right |

### Starter Code Info

Download project3_starter.zip and extract the files. The zip contains three
classes, `Main`, `CompleteBinaryTree`, and `InvalidTreeException`. You should be
able to create a new Java project and copy the classes into your project.

The `CompleteBinaryTree` class defines one instance variable named `root`, which
is an instance of the nested class `TreeNode`. Each `TreeNode` stores an integer
value, along with references to left and right nodes that anchor two two
subtrees.

The `Main` class contains a `main` method with code to create an instance of
`CompleteBinaryTree` from a sample array in integers. The tree is built using
the array in level-order, meaning the nodes are filled from top to bottom, left
to right, one level at a time. Here's how the positions in the array map to the
tree:

If a node is at index i in the array:

- Its left child is at index 2 \* i + 1
- Its right child is at index 2 \* i + 2

This pattern is used recursively to create the tree. Thus,
`Integer[] values = { 90, 70, 50, 20, 40 };` produces the following tree:

```
       90
      /  \
    70    50
   / \
  20 40
```

After creating the tree, the `main` method calls the `preorder` method to print
the values using a preorder traversal.

Run the `Main` class and confirm the output:

```text
Preorder: 90 70 20 40 50
```

It is important to understand the `CompleteBinaryTree` constructor and the
recursive helper method named `makeNode`. Try using a debugger to step through
the creation of the binary tree:

- Set a breakpoint in the `CompleteBinaryTree` constructor and use the IDE's
  debugger to **step into** each call to the `makeNode` method. Pay special
  attention to the value of the `index` parameter for each recursive call.
- Alternatively,
  <a href="https://pythontutor.com/render.html#code=public%20class%20CompleteBinaryTree%20%7B%0A%0A%20%20%20%20protected%20TreeNode%20root%3B%0A%0A%20%20%20%20public%20static%20class%20TreeNode%20%7B%0A%20%20%20%20%20%20%20%20protected%20Integer%20value%3B%0A%20%20%20%20%20%20%20%20protected%20TreeNode%20left%3B%0A%20%20%20%20%20%20%20%20protected%20TreeNode%20right%3B%0A%0A%20%20%20%20%20%20%20%20public%20TreeNode%28Integer%20value%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20this.value%20%3D%20value%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%0A%20%20%20%20public%20CompleteBinaryTree%28Integer%5B%5D%20array%29%20%7B%0A%20%20%20%20%20%20%20%20if%20%28array%20%3D%3D%20null%20%7C%7C%20array.length%20%3D%3D%200%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20root%20%3D%20null%3B%0A%20%20%20%20%20%20%20%20%7D%20else%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20root%20%3D%20makeNode%28array,%200%29%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%0A%20%20%20%20protected%20TreeNode%20makeNode%28Integer%5B%5D%20array,%20int%20index%29%20%7B%0A%20%20%20%20%20%20%20%20if%20%28index%20%3E%3D%20array.length%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20return%20null%3B%0A%20%20%20%20%20%20%20%20%7D%0A%20%20%20%20%20%20%20%20if%20%28array%5Bindex%5D%20%3D%3D%20null%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20throw%20new%20RuntimeException%28%22Node%20element%20must%20not%20be%20null%22%29%3B%0A%20%20%20%20%20%20%20%20%7D%0A%0A%20%20%20%20%20%20%20%20TreeNode%20node%20%3D%20new%20TreeNode%28array%5Bindex%5D%29%3B%0A%20%20%20%20%20%20%20%20node.left%20%3D%20makeNode%28array,%202%20*%20index%20%2B%201%29%3B%0A%20%20%20%20%20%20%20%20node.right%20%3D%20makeNode%28array,%202%20*%20index%20%2B%202%29%3B%0A%0A%20%20%20%20%20%20%20%20return%20node%3B%0A%20%20%20%20%7D%0A%0A%20%20%20%20public%20void%20preorder%28%29%20%7B%0A%20%20%20%20%20%20%20%20preorder%28root%29%3B%0A%20%20%20%20%20%20%20%20System.out.println%28%29%3B%0A%20%20%20%20%7D%0A%0A%0A%20%20%20%20private%20void%20preorder%28TreeNode%20root%29%20%7B%0A%20%20%20%20%20%20%20%20if%20%28root%20%3D%3D%20null%29%0A%20%20%20%20%20%20%20%20%20%20%20%20return%3B%0A%20%20%20%20%20%20%20%20System.out.print%28root.value%20%2B%20%22%20%22%29%3B%0A%20%20%20%20%20%20%20%20preorder%28root.left%29%3B%0A%20%20%20%20%20%20%20%20preorder%28root.right%29%3B%0A%20%20%20%20%7D%0A%20%20%20%20%0A%20%20%20%20public%20static%20void%20main%28String%5B%5D%20args%29%20%7B%0A%20%20%20%20%20%20%20%20%20%20%20%20Integer%5B%5D%20values%20%3D%20%7B%2090,%2070,%2050,%2020,%2040%20%7D%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20CompleteBinaryTree%20tree%20%3D%20new%20CompleteBinaryTree%28values%29%3B%0A%20%20%20%20%20%20%20%20%20%20%20%20tree.preorder%28%29%3B%0A%20%20%20%20%7D%0A%0A%7D&cumulative=false&curInstr=0&heapPrimitives=nevernest&mode=display&origin=opt-frontend.js&py=java&rawInputLstJSON=%5B%5D&textReferences=false">click
  this link</a> to use the Python Tutor code visualizer with a slightly adapted
  version of the code. Keep pressing the `Next` button to execute each line of
  code and view the method call stack and object structures.

<img src="images/tree1.png" alt="Binary Tree in nodes" width=300>

NOTE: Python tutor only allows one top-level public class. Other classes must
either be nested or not declared as public.

### Task 1. Create a second `CompleteBinaryTree` constructor.

Edit `CompleteBinaryTree` to add a **second constructor** with the following
signature:

```java
/**
 * Constructs a complete binary tree from a space-separated string of integers
 * given in level-order (i.e., top to bottom, left to right).
 *
 * The string must contain valid integer values only, separated by whitespace.
 * These values are parsed and used to build the tree in level-order.
 *
 * @param levelOrderValues a whitespace-delimited string of integer values representing
 *                         nodes in level-order
 * @throws InvalidTreeException if any token in the input is not a valid integer
 */
public CompleteBinaryTreeSolution(String levelOrderValues) throws InvalidTreeException
```

The constructor should create a complete binary tree from the parameter string
by implementing the following algorithm:

1.  Remove leading/trailing whitespace and split the parameter string into an
    array of strings, using any sequence of whitespace (spaces, tabs, etc.) as
    the delimiter.
2.  Create an array of Integer objects from the array of String objects. If a
    string does not represent a valid integer, throw a custom exception
    `InvalidTreeException` with an appropriate message such as "Node value must
    be an integer.".
3.  Call the other `CompleteBinaryTree` constructor to create a new tree,
    passing the array of Integer objects as an argument.
4.  Set the root of `this` tree to the root of the tree created in step 3.

Next, evolve the `main` method to prompt the user for a string and call the new
constructor:

```java
// Integer[] values = { 53, 28, 83, 11, 61, 67 };
// CompleteBinaryTree tree = new CompleteBinary(values);
System.out.print("Enter a binary tree: ");
String treeString = input.nextLine();
CompleteBinaryTree tree = new CompleteBinaryTree(treeString);
tree.preorder();
```

Test your code with valid input:

```text
Enter a binary tree: 90 70 50 20 40
Preorder: 90 70 20 40 50
```

Test your code with invalid input:

```text
Enter a binary tree: 50 30 abc 99
Node value must be an integer.
```

### Task 2. Evolve the `preorder` method to indent based on the node's level in the tree.

```
       90
      /  \
    70    50
   / \   / \
  20 40 10 25
```

Currently, the `preorder` method prints the node values on a single line,
following a preorder traversal:

```text
Enter a binary tree: 90 70 50 20 40 10 25
Preorder: 90 70 20 40 50 10 25
```

Update the recursive `preorder` helper method so it prints each node’s value on
a separate line with indentation that reflects its depth in the tree. This
indentation visually aligns nodes by level, producing an indented, top-down
representation of the tree’s structure. For example:

```text
Enter a binary tree: 90 70 50 20 40 10 25
Preorder:
90
    70
        20
        40
    50
        10
        25
```

To do this, modify the recursive helper method to take a second parameter
representing the current depth level. Use that value to print 4 spaces per level
before the node’s value. The root node starts at level 0, its immediate children
at level 1, and so on.

```java
/**
 * Recursive helper method for preorder traversal.
 * Prints each node's value with indentation based on its depth level
 * to visually represent the tree structure.
 *
 * @param root  the current subtree root
 * @param level the indentation level, increases with depth in the tree
 */
private void preorder(TreeNode root, int level)
```

---

### Task 3. Check if a complete binary tree is a binary heap.

#### Example:

```java
String[] words = {"i", "love", "a", "good", "BOOK", "and", "LOVE", "sad", "BooK", "book"};
Set<String> stopWords = new HashSet<>(
   Arrays.asList("the", "is", "in", "at", "of", "and", "a", "to", "it", "or", "was", "so"));

System.out.println(NLPUtility.countFilteredWords(words, stopWords));
//{book=3, good=1, i=1, love=2, sad=1}
```

---

### Task 3. `public static LinkedHashMap<String, Integer> sortByValueDescending(Map<String, Integer> map)`

**Returns a `LinkedHashMap` sorted by frequency in descending order. For ties,
maintains the original order of keys as they appear in the map.**

Algorithm:

1. Convert the word map entries to a list for sorting
2. Sort the list of entries in descending order based on value (frequency)
3. Create a LinkedHashMap and insert the sorted entries to maintain their order.

#### Example:

```java
Map<String, Integer> wordMap = new TreeMap<>();
wordMap.put("book", 3);
wordMap.put("good", 1);
wordMap.put("i", 1);
wordMap.put("love", 2);
wordMap.put("sad", 1);

System.out.println(wordMap); // {book=3, good=1, i=1, love=2, sad=1}

System.out.println(NLPUtility.sortByValueDescending(wordMap)); // {book=3, love=2, good=1, i=1, sad=1}
```

---

### Task 4. `public static String getSentimentFromFrequencies(Map<String, Integer> wordMap, Set<String> positiveWords, Set<String> negativeWords)`

**Sums the total frequencies of words in the corresponding positive and negative
word sets. Returns a summary string in the format "Positive: X, Negative: Y".**

#### Example:

```java
Map<String, Integer> wordMap2 = new LinkedHashMap<>();
wordMap2.put("book", 3);
wordMap2.put("love", 2); // positive
wordMap2.put("good", 1); // positive
wordMap2.put("i", 1);
wordMap2.put("sad", 1); // negative
System.out.println(wordMap2); // {book=3, love=2, good=1, i=1, sad=1}

Set<String> positiveWords = new HashSet<>(Arrays.asList("good", "great", "happy", "love", "like"));
Set<String> negativeWords = new HashSet<>(Arrays.asList("bad", "terrible", "horrible", "sad", "hate"));

System.out.println(NLPUtility.getSentiment(wordMap2, positiveWords, negativeWords));// Positive: 3, Negative: 1
```

---

### Task 5. `public static Map<String, Object> getWordsWithMaxFrequency(Map<String, Integer> wordMap)`

**Returns a map containing an alphabetically sorted list of words that appear
most frequently in the given word map, along with the corresponding frequency.**

Algorithm:

- Finds the maximum frequency value in the input map
- Collect a list of all words that occur with that frequency
- Sorts the list alphabetically
- Returns a new map with two entries having the following keys:
  - "words": a list of most frequent words, sorted alphabetically
  - "frequency": the maximum frequency as an integer

**Note:** The returned map contains two entries with `String` keys: `"words"`
and `"frequency"`.

- The value associated with `"words"` is a `List<String>` containing the most
  frequently occurring words.
- The value for `"frequency"` is an `Integer` representing the highest frequency
  found.

Because the values are of different types (`List<String>` and `Integer`), the
method returns a map of type `Map<String, Object>`.

#### Example:

```java
Map<String, Integer> wordMap3 = new LinkedHashMap<>();
wordMap3.put("good", 1);
wordMap3.put("i", 1);
wordMap3.put("love", 3);
wordMap3.put("book", 3);
wordMap3.put("sad", 1);
System.out.println(wordMap3); // {good=1, i=1, love=3, book=3, sad=1}

System.out.println(NLPUtility.getWordsWithMaxFrequency(wordMap3)); // {words=[book, love], frequency=3}

```

Note that the map passed as a parameter may not be sorted by frequency or word.
Your method will have to find the maximum frequency, along with all words that
are mapped to that frequency.

---

## 🧪 Sample Program Flow

```text
Enter a paragraph of text:
I really love a good book, and You REALLY love a sad movie.  We both ReAllY LOVE going for a walk!

Tokenized:
[I, really, love, a, good, book, and, You, REALLY, love, a, sad, movie, We, both, ReAllY, LOVE, going, for, a, walk]

Word map sorted by key ascending:
book:1
both:1
for:1
going:1
good:1
i:1
love:3
movie:1
really:3
sad:1
walk:1
we:1
you:1

Word map sorted by value descending:
love:3
really:3
book:1
both:1
for:1
going:1
good:1
i:1
movie:1
sad:1
walk:1
we:1
you:1

Sentiment: Positive: 4, Negative: 1

Most frequent word(s): [love, really] (used 3 times)
```

---

## 🚫 Example: Empty or Non-Meaningful Input

```text
Enter a paragraph of text:
SO is.!  It????

Tokenized:
[SO, is, It]

No valid words found.
```

## Submitting your solution

You are to submit two files.

1. The first is a `.zip` file that contains all the source code for the project.
   The `.zip` file should contain only source code and nothing else, which means
   only the `.java` files. If you elect to use a package the `.java` files
   should be in a folder whose name is the package name. Every outer class
   should be in a separate `.java` file with the same name as the class name.
   Each file should include a comment block at the top containing your name, the
   project name, the date, and a short description of the class contained in
   that file.

2. The second is a Word document (PDF or RTF is also acceptable) that contains
   the documentation for the project, which should include the following:
   - A UML class diagram that includes all classes.
   - A test plan that includes test cases that you have created indicating what
     aspects of the program each one is testing.
     - Conduct unit tests for each method within the `NLPUtility` class. You may
       want to develop separate test classes (include "Test" in the class name,
       and/or place in a separate package) to individually call each method in
       isolation. Include screenshots that capture the result of your unit
       tests. Ensure your test cases sufficiently demonstrate each method
       returns a sorted result when sorting is required.
   - A short paragraph on lessons learned from the project.
