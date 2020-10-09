import { ModelAggregate, AggregatesSetting } from './../../../model/model-aggregate';
import {
  FormArray,
  FormGroup,
  FormBuilder,
  Validators
} from '@angular/forms';
import {
  Component,
  OnInit
} from '@angular/core';
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-aggregates-settings',
  templateUrl: './aggregates-settings.component.html',
  styleUrls: ['./aggregates-settings.component.css']
})
export class AggregatesSettingsComponent implements OnInit {

  aggregateSettingsForm: FormGroup;
  stateFields: FormArray;
  enableAdd = false;
  stateFieldsTypes = ['int', 'double', 'String', 'float', 'short', 'byte', 'boolean', 'long', 'char'];
  creator = {
    stateFields: this.createStateField,
    events: this.createEvents,
    methods: this.createMethods,
    api: this.createApi
  };
  aggregatesSettings: AggregatesSetting[] = [];

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
  }

  removeRow(type: string, index: number): void {
    (this.aggregateSettingsForm.get(type) as FormArray).removeAt(index);
  }

  get formStateFields(): FormArray {
    return this.aggregateSettingsForm.get('stateFields') as FormArray;
  }

  get formEvents(): FormArray {
    return this.aggregateSettingsForm.get('events') as FormArray;
  }

  get formMethods(): FormArray {
    return this.aggregateSettingsForm.get('methods') as FormArray;
  }

  get formApi(): FormArray {
    return this.aggregateSettingsForm.get('api') as FormArray;
  }

  enableNewAggregate(flag: boolean){
    this.enableAdd = flag;
    if (flag) {
      this.aggregateSettingsForm = this.formBuilder.group({
        aggregateName: ['', [Validators.required]],
        stateFields: this.formBuilder.array([this.createStateField(this.formBuilder)]),
        events: this.formBuilder.array([this.createEvents(this.formBuilder)]),
        methods: this.formBuilder.array([this.createMethods(this.formBuilder)]),
        api: this.formBuilder.array([this.createApi(this.formBuilder)])
      });
    }
  }

  add(){
    this.aggregatesSettings.push(this.aggregateSettingsForm.value as AggregatesSetting);
    this.enableAdd = false;
  }

  addNewRow(type: string){
    const formArray = this.aggregateSettingsForm.get(type) as FormArray;
    formArray.push(this.creator[type](this.formBuilder));
  }

  private createStateField(formBuilder: FormBuilder): FormGroup{
    return formBuilder.group({
      name: ['', [Validators.required]],
      type: ['', [Validators.required]],
    });
  }

  private createEvents(formBuilder: FormBuilder): FormGroup{
    return formBuilder.group({
      name: ['', [Validators.required]],
      fields: [formBuilder.group(
        ['', [Validators.required]]
      )]
    });
  }

  private createMethods(formBuilder: FormBuilder): FormGroup{
    return formBuilder.group({
      name: ['', [Validators.required]],
      factory: ['', [Validators.required]],
      parameters: [formBuilder.group(
        ['', [Validators.required]]
      )],
      event: ['', [Validators.required]]
    });
  }

  private createApi(formBuilder: FormBuilder): FormGroup{
    return formBuilder.group({
      rootPath: ['', [Validators.required]],
      parameters: [formBuilder.group({
        path: ['', [Validators.required]],
        httpMethod: ['', [Validators.required]],
        aggregateMethod: ['', [Validators.required]],
        requireEntityLoad: ['', [Validators.required]],
      })],
    });
  }
}
