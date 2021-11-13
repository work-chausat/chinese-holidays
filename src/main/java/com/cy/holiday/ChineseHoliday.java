package com.cy.holiday;

import com.cy.holiday.util.PropertyUtil;
import com.cy.holiday.model.Event;
import com.cy.holiday.util.EventUtil;
import com.cy.holiday.util.LinkUtil;
import com.cy.holiday.util.CalenderUtil;

import java.util.ArrayList;
import java.util.List;

public class ChineseHoliday {

    public static void main(String[] args) {
        // Get config properties
        String searchLink = PropertyUtil.get("search.link");
        String searchReg = PropertyUtil.get("search.reg");
        int searchCount = Integer.parseInt(PropertyUtil.get("search.count"));
        String holidayReg = PropertyUtil.get("holiday.reg");

        List<Event> events = new ArrayList<>();

        // Get holiday announcement links from search link.
        List<List<String>> links = LinkUtil.getContents(searchLink, searchReg, searchCount);

        // Get holiday contents from each holiday announcement link.
        for (List<String> link : links) {
            System.out.println(link);
            List<List<String>> contents = LinkUtil.getContents(link, holidayReg);

            // Get holiday details from each holiday content.
            for (List<String> content : contents) {
                System.out.println(content);
                events.addAll(EventUtil.getEvents(content));
            }
        }

        // Generate ics file.
        CalenderUtil.generateCalender(events);

        System.exit(0);
    }
}
