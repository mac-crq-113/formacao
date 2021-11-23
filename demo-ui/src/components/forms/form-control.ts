import { bindable, BindingMode } from "@aurelia/runtime-html";

export class FormControlCustomElement {

    @bindable()
    private compId: string;

    @bindable()
    private compLabel: string;

    @bindable()
    private compType: string;

    @bindable()
    private compPlaceholder: string;

    @bindable({ mode: BindingMode.twoWay })
    private compValue: string;

    @bindable()
    private compRequired: string;
}