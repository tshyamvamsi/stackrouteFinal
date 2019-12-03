import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Match } from '../../match';
import { MatchService} from '../../match.service';
@Component({
  selector: 'app-matchrecommendations',
  templateUrl: './matchrecommendations.component.html',
  styleUrls: ['./matchrecommendations.component.css']
})
export class MatchrecommendationsComponent implements OnInit {

  matches: Array<Match>;
  constructor(private matchService: MatchService, private dialog: MatDialog) { 
    console.log('inside match recommendation constructor ....');
    this.matches = [];
   }

  ngOnInit() {
    
    this.getMatcheRecommendationsList();

  }

  getMatcheRecommendationsList(){
    this.matchService.getMatcheRecommendations().subscribe((matches => {
      this.matches.push(...matches);

      console.log('matchRecommendation --->',this.matches);
    }));
  }
}
