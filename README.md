# Windows-Paint-Application


Project JPaint Application mimicking several features similar to the Microsoft’s Paint application using Java programming language. 
The aim of this project is to learn and acknowledge the various design patterns, SOLID principles, and enhance Object-Oriented programming techniques. 
The JPaint application is built using five design patterns, which are abstract factory pattern, command pattern, two strategy patterns, and composite pattern.
The design patterns are included in the respository. 

**Features Implemented**-----

The required features that are built into the JPaint application includes:

• Draw shape(s)→including rectangle, ellipse, and triangle shape through creating IShape objects with various colors and shading types.

• Select shape(s) →able to select the various shapes drawn with dashed outlined border around the shapes indicating that the shape(s) are selected.
• Move shape(s)→able to move the selected shape(s) to another position on the paint canvas.

• Copy shape(s)→able to copy selected shape(s), by creating new objects of the shapes.

• Paste shape(s) → able to paste the copied shape(s) on to the paint canvas.

• Delete shape(s)→able to delete selected shape(s).

• Group shape(s)→able to group shapes selected into a single object and able to
function as a whole when applying the various command features on the shapes.

• UnGroup shape(s)→ungroup the grouped shapes selected back to individual shape
objects.

• Undo & Redo→able to undo and redo triggered events including draw, move, copy,
paste, delete, group, and ungroup shape(s).

• Clear→clear the paint canvas for new drawing or other events, as well as, more convenient for manual testing purposes.



**Design Patterns**-----

-Abstract Factory Pattern -> used to create various shapes including Rectangle, Ellipse, and Triangle. 
This allows to easy modify or add new class shapes without changing existing code. 

-Command Pattern -> used for features including create shape, move shape, copy shape, paste shape, delete shape, and other features. 
Implementing the Command Pattern allows triggering of various events or commands, especially for Undo and Redo in this project’s case in storing the command histories. 
Command pattern allows encapsulation of events or commands, which makes the project more manageable due to the abundant amount of commands and hide unnecessary information from the client side.

-Strategy Pattern -> used for drawing the different types of shapes on the paint canvas including filled or soild colored shapes or outlined shapes.
Implementing the strategy pattern on drawing shapes allows encapsulating behavior of modifying or doing something to the object and allows code to be more manageable.

-Composite Pattern -> used for selecting shape(s) in a group feature. Essentially, this pattern is good for combining objects together as one and process them as one group.


