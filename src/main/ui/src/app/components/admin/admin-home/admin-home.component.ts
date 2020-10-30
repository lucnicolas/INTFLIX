import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {SeriesService} from "../../../service/series/series.service";
import {Series} from "../../../model/series/series";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.sass']
})
export class AdminHomeComponent implements OnInit {

  selectedSeries: Series;
  seriesList: Series[];
  showDetails: boolean;
  closeResult: string;

  constructor(private router: Router,
              private modalService: NgbModal,
              private seriesService: SeriesService) { }

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
    this.selectedSeries = (series != null) ? series : new Series();

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

  onDelete(series: Series): void {
    this.seriesService.delete(series).subscribe(() => this.getAllSeries());
  }

}
