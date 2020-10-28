  export interface StateField {
    name: string;
    type: string;
  }

  export interface AggregateEvent {
    name: string;
    fields: string[];
  }

  export interface Method {
    name: string;
    factory: string;
    parameters: string[];
    event: string;
  }

  export interface Route {
    path: string;
    httpMethod: string;
    aggregateMethod: string;
    requireEntityLoad: boolean;
  }

  export interface Api {
    rootPath: string;
    routes: Route[];
  }

  export interface AggregateSetting {
    aggregateName: string;
    stateFields: StateField[];
    events: AggregateEvent[];
    methods: Method[];
    api: Api;
  }

  export interface Persistence {
    storageType: string;
    useCQRS: boolean;
    projections: string;
    database: string;
    commandModelDatabase: string;
    queryModelDatabase: string;
  }

  export interface Model {
    aggregateSettings: AggregateSetting[];
    persistence: Persistence;
  }

  export interface RootObject {
    model: Model;
  }
