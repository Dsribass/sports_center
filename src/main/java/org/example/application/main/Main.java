package org.example.application.main;

import org.example.domain.entities.sports_court.CourtSize;
import org.example.domain.entities.sports_court.SportsCourt;
import org.example.domain.entities.sports_court.soccer_court.SoccerCourt;
import org.example.domain.entities.sports_court.soccer_court.TypeOfSoccer;

public class Main {
    public static void main(String[] args) {
        SoccerCourt soccerCourt = new SoccerCourt();
        soccerCourt.setCourtSize(CourtSize.SMALL);
        soccerCourt.setCourtValue(160.00);
        soccerCourt.setIndoorCourt(true);
        soccerCourt.setTypeOfSoccer(TypeOfSoccer.FIELD);
        System.out.println(soccerCourt.getCapacity());
    }
}
