export interface Item {
    code: string;
    itemType: string;
    containerType : string;
    createData: Date;
    state:string;
    needFridge: boolean;
    weight: number;
    name: string;
    clientId: number;
    action?: string;

}

export interface CreateItem {
    itemType: string;
    containerType : string;
    needFridge: boolean;
    weight: string;
    name: string;

}

export interface UpdateItem {
    containerType : string;
    needFridge: boolean;
    weight: string;
    name: string;
    state: string;
}
