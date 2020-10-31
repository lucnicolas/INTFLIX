import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {SeriesService} from "../../../service/series/series.service";
import {Series} from "../../../model/series/series";
import {Season} from "../../../model/season/season";
import {SeasonsService} from "../../../service/seasons/seasons.service";
import {count} from "rxjs/operators";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.sass']
})
export class AdminHomeComponent implements OnInit {

  selectedSeries: Series;
  seriesToDelete: Series;
  seriesList: Series[];

  seasonsOfSelectedSeries: Season[];

  showDetails: boolean;
  closeResult: string;
  countSeasons: number;

  constructor(private router: Router,
              private modalService: NgbModal,
              private seriesService: SeriesService,
              private seasonsService: SeasonsService) { }

  ngOnInit(): void {
    this.getAllSeries();
  }

  getAllSeries(): void {
    this.seriesService.findAll().subscribe(data => { this.seriesList = data });
  }

  /**
   * Open a series modal
   * @param content Modal content
   * @param series If unset create a new series else allow to edit it.
   * @param showDetails If true disable all fields
   */
  open(content: any, series?: Series, showDetails?: boolean) {

    this.showDetails = (showDetails) ? this.showDetails = true : false;
    if (series != null) {
      this.selectedSeries = series;
      this.seasonsOfSelectedSeries = this.selectedSeries.fkSeries
    } else {
      this.selectedSeries = new Series();
    }

    this.modalService
      .open(content, { ariaLabelledBy: 'modal-basic-title',})
      .result
      .then(
        (result) => { this.closeResult = `Closed with: ${result}`; },
        (reason) => { this.closeResult = `Dismissed ${AdminHomeComponent.getDismissReason(reason)}`;} )
      .then(() => this.getAllSeries());
  }

  private static getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  onSubmit(): void {
    this.seriesService.save(this.selectedSeries).subscribe(() => {
      this.getAllSeries();
      this.modalService.dismissAll();
    });
  }

  openDelete(targetModal, series: Series) {
    this.seriesToDelete = series;
    this.modalService.open(targetModal, {
      backdrop: 'static',
    });
    document.getElementById('delete_modal_text').innerHTML='Are you sure you want to delete <i>'+series.name+ '</i> series ?'
  }

  deleteSelectedSeries(): void {
    this.seriesService.delete(this.seriesToDelete).subscribe(() => {
      this.getAllSeries();
      this.modalService.dismissAll();
      this.seriesToDelete = null;
    });
  }

  addNewSeason() {

  }

}
