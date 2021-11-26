import Aurelia, { RouterConfiguration } from 'aurelia';
import * as GlobalElements from './components/global-elements';
import { DemoApp } from './demo-app';

Aurelia
  .register(RouterConfiguration)
  .register(GlobalElements)
  .app(DemoApp)
  .start();
