#Makefile for Assignment1 
#Elias Sihlangu 
#16 August 2021

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
 
CLASSES=tool.class  sequentialFilter.class parallelFilter.class  
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
        
runsfilter: $(CLASS_FILES)
	java -cp $(BINDIR) sequentialFilter
	
runpfilter: $(CLASS_FILES)
	java -cp $(BINDIR) parallelFilter 

