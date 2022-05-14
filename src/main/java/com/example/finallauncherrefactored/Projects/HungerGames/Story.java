package com.example.finallauncherrefactored.Projects.HungerGames;

import java.util.ArrayList;

class Story
{
    //Pages
    Jungle jungle; 
    Weapons weapons;
    Page1 page1;
    Page2 page2;
    Page3 page3;
    Page4 page4;
    Page5 page5;
    Page6 page6;
    Page7 page7;
    Page8 page8;
    Page9 page9;
    Page10 page10;
    Page11 page11;
    

    int currentPageNumber;

    //Characters

    // Containers for pages and characters
    ArrayList<Page> book = new ArrayList<Page>();
    ArrayList<Character> characters = new ArrayList<Character>();
    

    Story()
    {
        // Set up pages and give each page a reference to this story
        jungle = new Jungle(this);
        weapons= new Weapons(this);
        page1 = new Page1(this);
        page2 = new Page2(this);
        page3 = new Page3(this);
        page4 = new Page4(this);
        page5 = new Page5(this);
        page6 = new Page6(this);
        page7 = new Page7(this);
        page8 = new Page8(this);
        page9 = new Page9(this);
        page10 = new Page10(this);
        page11 = new Page11(this);

        book.add(jungle);
        book.add(weapons);
        book.add(page1);
        book.add(page2);
        book.add(page3);
        book.add(page4);
        book.add(page5);
        book.add(page6);
        book.add(page7);
        book.add(page8);
        book.add(page9);
        book.add(page10);
        book.add(page11);

        // Set up characters and give each character a reference to this story

        currentPageNumber = 0;
    }

    Page getCurrentPage()
    {
        if (currentPageNumber >= 0 && currentPageNumber < book.size())
        {
            return book.get(currentPageNumber);
        }
        else
        {
            return null;
        }
    }

    void readCurrentPage()
    {
        Page p = getCurrentPage();
        if (p != null)
        {
            p.read();
        }
    }

    void turnPage()
    {
        // Change the page number. Prevent turning past the end of the book!
        if (currentPageNumber < book.size())

        {
            currentPageNumber++;
        }
    }

    void turnBackPage()
    {
        // Change the page number. Prevent turning past the beginning of the book!
        if (currentPageNumber > 0)

        {
            currentPageNumber--;
        }
    }
}