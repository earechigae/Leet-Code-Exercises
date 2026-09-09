package org.codesignal.exercises.logexercises;

import java.util.*;

/*
Imagine you have a large mailbox that receives emails from various sources and you need to organize these emails.
Your task involves implementing a Java method named organizeInbox. This method will accept a string of emails as input and output a list of strings.
Each string contains two elements: the sender's email address and the total count of emails received from this sender, separated by a space.

Each email is represented by various metadata separated by commas, such as "Sender Email Address, Subject, Timestamp".
The total string comprises these entries, separated by semicolons.
Emails originate from distinct senders and can occur at any timestamp in the "HH:MM" format within a 24-hour range.

Here is the format of the string:
"Sender Email Address1, Subject1, 09:00; Sender Email Address2, Subject2, 10:00; Sender Email Address1, Subject3, 12:00"

The method should return: ["Sender Email Address1 2", "Sender Email Address2 1"].

For each input entry, the sender's email is a string containing up to 2020 characters.
The timestamp follows the "HH:MM" format. The total number of email entries varies from 1 to 500, inclusive.

Your method must:
    1. Extract the sender's email address.
    2. Count the number of emails received from each sender.
    3. Output a list of strings, where each string contains the sender's email address followed by the count of emails
        received from them, separated by a space.
    4. Sort the list in descending order of the counts. If two senders have sent the same number of emails,
        list them in ascending order based on the senders' email addresses.

The sender's email address is always followed by a comma, a space, and the start of the subject line.
The subject line is always followed by a comma, a space, and the timestamp.
All emails are unique, meaning there will be no emails with the same subject and timestamp from the same sender.
 */

public class OrganizeInbox {
    public List<String> organizeInbox(String inboxString) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> senderCounts = new HashMap<>();
        String emails[] = null;
        String senderEmail = null;

        if(inboxString == null || inboxString.trim().isEmpty()){
            return result;
        }
        emails = inboxString.split(";");
        for(String email: emails){
            senderEmail = email.split(",")[0].trim();
            senderCounts.put(senderEmail, senderCounts.getOrDefault(senderEmail, 0) + 1);
        }

        // Convert the map to a list of strings and sort it
        for (Map.Entry<String, Integer> entry : senderCounts.entrySet()) {
            result.add(entry.getKey() + " " + entry.getValue());
        }
        result.sort((a, b) -> {
            String[] partsA = a.split(" ");
            String[] partsB = b.split(" ");
            int countA = Integer.parseInt(partsA[1]);
            int countB = Integer.parseInt(partsB[1]);
            if (countA != countB) {
                return Integer.compare(countB, countA); // Sort by count in descending order
            }
            return partsA[0].compareTo(partsB[0]); // Sort by email in ascending order
        });

        return result;
    }

    public static void main(String args[]){
        String input = "alice@example.com, Hello, 09:00; bob@example.com, Meeting, 10:00; alice@example.com, Re: Hello, 12:00";
        OrganizeInbox organizer = new OrganizeInbox();
        List<String> result = organizer.organizeInbox(input);
        System.out.println(result);
    }
}
