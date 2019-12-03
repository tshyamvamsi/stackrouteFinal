import { Injectable } from '@angular/core';
import {Match} from './match';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import {map} from 'rxjs/operators/';
import { retry } from 'rxjs/internal/operators/retry';
import { Matchdetails } from './matchdetails';
import { USER_ID } from '../authentication/authentication.service';


@Injectable({
  providedIn: 'root'
})
export class MatchService {

  cricAPIEndpoint : string;

  apikey: string;
  quickviewEndpoint: string;
  springappEndpoint: string;
  matchDetailEndpoint : string;
  matchRecommendationStartpoint: string;
  description : string;
  userId: string;

  constructor(private http : HttpClient) {
    this.cricAPIEndpoint = 'http://cricapi.com/api/matches';

    this.apikey = 'Cg9lC71sMhQ02FwO1q6maNJdw9l2';
    this.quickviewEndpoint = 'http://cricapi.com/api/matchCalendar';
    // this.springappEndpoint = 'http://localhost:8080/api/v1/matchservice';

    this.springappEndpoint = 'http://localhost:8086/favouriteservice/api/v1/matchservice';
    this.matchDetailEndpoint = 'http://cricapi.com/api/cricketScore';

    //this.matchRecommendationStartpoint='http://localhost:8070/api/v1/matchservice/';

    this.matchRecommendationStartpoint='http://localhost:8086/matchrecommendationservice/api/v1/matchservice/';

    




   }

   getMatches(): Observable<Array<Match>>{
    console.log('inside getMatches');
     const matchesEndpoint = this.cricAPIEndpoint +'?apikey='+this.apikey;
     console.log(matchesEndpoint);
     return this.http.get(matchesEndpoint).pipe(
      retry(3),
      map(this.pickMatchesResult),
      map(this.transformMatchData.bind(this))/*  */
    );
     //return this.http.get(matchesEndpoint);
       }

   pickMatchesResult(response){
     console.log('matches in match service----->',response['matches']);
    return response['matches'];
  }

  /*  */

  transformMatchData(matches): Array<Match>{
   let matchArray = matches.map(matchFromAPI => {
    let match = new Match();
    match.teamOne = matchFromAPI['team-1'];
    match.teamTwo = matchFromAPI['team-2'];
    match.matchDate = matchFromAPI['date'];
    match.matchStarted = matchFromAPI['matchStarted'];
    match.unique_id = matchFromAPI['unique_id'];
    return match;
  });

  return matchArray;
 }


   addMatchToFavourite(match){
     this.userId = sessionStorage.getItem(USER_ID);
    console.log('inside match service... ready to send match data to server....', match);
    return this.http.post(this.springappEndpoint + '/' + this.userId + "/match", match);
   }


   getFavouritelistedMatches(): Observable<Array<Match>>{
     console.log('inside getFavouritelistedMatches');
     this.userId = sessionStorage.getItem(USER_ID);
     return this.http.get<Array<Match>>(this.springappEndpoint  + '/' + this.userId + "/matches");
   }

   /**
    * Quick view
    */
   getMatchesQuickView(): Observable<Array<Match>>{
    console.log('inside getMatchesQuickView');
    const calendarAPIUrl = this.quickviewEndpoint + '?apikey='+this.apikey
    console.log('calendarAPIUrl -- ', calendarAPIUrl);
    return this.http.get<Array<Match>>(calendarAPIUrl).pipe(
      retry(3),
      map(this.pickQuickViewMatchesResult),
      map(this.transformQuickViewData.bind(this))/*  */
    );
  }


 pickQuickViewMatchesResult(response){
   console.log('quick view matches in match service----->',response['data']);
  return response['data'];
}

  transformQuickViewData(matches): Array<Match>{
  let matchArray = matches.map(matchFromAPI => {
    let match = new Match();
    match.teamOne = matchFromAPI['team-1'];
    match.teamTwo = matchFromAPI['team-2'];
    match.matchDate = matchFromAPI['date'];
    match.matchStarted = matchFromAPI['matchStarted'];
    let unqueId = matchFromAPI['unique_id'];
    if(unqueId === 'will generate 1-2 days before match'){
      match.unique_id = '';
    }
    else{
      match.unique_id = matchFromAPI['unique_id'];
    }
    match.name = matchFromAPI['name'];
    return match;
  });

  return matchArray;
  }

  deleteMatchFromFavouritelist(matchId){
    this.userId = sessionStorage.getItem(USER_ID);
     const url = this.springappEndpoint  +  '/' + this.userId + '/' + matchId;
     console.log(url);
     return this.http.delete(url);
   }

   getMatchDetails(uniqueId): Observable<Matchdetails>{

    console.log('service------inside getMatchDetails');
    const mtachDetailURL = this.matchDetailEndpoint + '?apikey=' + this.apikey + '&unique_id=' + uniqueId;
    console.log('mtachDetailURL -- ', mtachDetailURL);
    console.log('http call   ' , this.http.get<Matchdetails>(mtachDetailURL));
    return this.http.get<Matchdetails>(mtachDetailURL).pipe(
      retry(3),
      map(this.transformMatchDetailData.bind(this))/*  */
    );

  }

  transformMatchDetailData(matchData): Matchdetails{

      let matchDetails = new Matchdetails();
      matchDetails.teamOne = matchData['team-1'];
      matchDetails.teamTwo = matchData['team-2'];
      matchDetails.score = matchData['score'];
      matchDetails.matchStarted = matchData['matchStarted'];

       let desc = matchData['description'];
       console.log('description---> ', desc);
       //desc = desc.replace(/&amp;/g, '&')

      matchDetails.description = desc;

      return matchDetails;

    }


    getMatcheRecommendations(): Observable<Array<Match>>{
      console.log('inside getMatcheRecommendations');
      this.userId = sessionStorage.getItem(USER_ID);
      return this.http.get<Array<Match>>(this.matchRecommendationStartpoint  + this.userId + "/matchrecommendations");
    }
 
}
