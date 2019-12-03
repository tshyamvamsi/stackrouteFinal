import { Component, OnInit, Input } from '@angular/core';
import { Match } from '../../match';
import { MatchService} from '../../match.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'match-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  statusCode: number;
  errorStatus: string;
  @Input()
  matches: Array<Match>;
  @Input()
  quickviewAPI: boolean;
  @Input()
  favouriteAPI: boolean;
  @Input()
  matchRecommendationAPI: boolean;

  constructor(private matchService: MatchService, private snackBar: MatSnackBar) {

  }

  ngOnInit() {

  }

  addMatchToFavourite(match){
    console.log('addMatchToFavourite    ');

    this.matchService.addMatchToFavourite(match).subscribe((match) => {
      console.log(match);
      this.statusCode = match.status;
      if (this.statusCode === 201) {
        this.statusCode = 409; 
        console.log('409 status code -->', this.statusCode);
        this.snackBar.open("Successfully added match to favourite.", '',{
          duration:2000,
        });
      }else if (this.statusCode === 409) {
       // this.statusCode = 201;
        console.log('201 status code -->', this.statusCode);
        this.snackBar.open("Match already added to favourite.", '', {
        duration: 1000});

        
    }
   } );
  }
   
  deleteMatchFromFavouritelist(deleteMatch){
    console.log('delete match from container .id...' + deleteMatch.id);

   this.matchService.deleteMatchFromFavouritelist(deleteMatch.id).subscribe((match) => {
      this.snackBar.open('Match deleted successfully.', '',{
        duration: 2000,
      });
    });
    let message = `Match deleted from your favourite list`;
    let count = 0;
    this.matches.forEach(match => {

      if(match.unique_id === deleteMatch.unique_id){

        this.matches.splice(count, 1);
      }
      count ++;
    });
  }


}
