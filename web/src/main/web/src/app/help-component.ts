import {Component} from '@angular/core';

@Component({
    template: `
        <div><h2><strong>Description</strong></h2>
            <p>This is a simple application to allow a user to add, list, sort and edit a collection of servers stored
                in a database.</p>
            <p>It consists of two elements, a web interface and a file pickup.</p>
            <p><strong>Web Interface</strong></p>
            <ul>
                <li><strong>List Servers</strong>
                    <ul>
                        <li>Click on the Server List link on the nav bar.</li>
                        <li>To sort click on column header.</li>
                        <li>To edit click
                            <mat-icon>edit</mat-icon>
                            , enter the name and description and click
                            <mat-icon>done</mat-icon>
                            to save
                        </li>
                        <li>To delete a server click on
                            <mat-icon>delete</mat-icon>
                            .
                        </li>
                    </ul>
                </li>
                <li><strong>Add Server</strong>
                    <ul>
                        <li>Click on the Add Server link on the nav bar.</li>
                        <li>Fill in the server details.</li>
                        <li>Click save.</li>
                        <li>To cancel click cancel.</li>
                    </ul>
                </li>
                <li><strong>Count Servers</strong>
                    <ul>
                        <li>Click on the Server Count link on the nav bar.</li>
                        <li>Total number of servers will be displayed.</li>
                    </ul>
                </li>
            </ul>
            <p><strong>File Pickup</strong></p>
            <ul>
                <li><strong>Configure File Pickup</strong>
                    <ul style="list-style-type: circle;">
                        <li>Set file pickup in the applicatoion.properties file.</li>
                        <li>set&nbsp;myapp.enable.scheduling=true</li>
                        <li>set myapp.pickup.timer=&lt;poll frequency in miliseconds&gt;</li>
                        <li>myapp.pickup.location=&lt;pickup location&gt;</li>
                    </ul>
                </li>
            </ul>
            <ul>
                <li><strong>File Pickup</strong>
                    <ul>
                        <li>Drop an xml file into the specified location</li>
                        <li>The file must conform to:
                            <textarea rows="12" cols="140" style="resize: none;" readonly>
<?xml version="1.0" encoding="UTF-8"?>
                                <xs:schema attributeFormDefault="unqualified" elementFormDefault="qualified"
                                           targetNamespace="http://www.opsource.net/simpleapp"
                                           xmlns:xs="http://www.w3.org/2001/XMLSchema">
  <xs:element name="server" type="sim:serverType" xmlns:sim="http://www.opsource.net/simpleapp"/>
  <xs:complexType name="serverType">
    <xs:sequence>
      <xs:element type="xs:string" name="id"/>
      <xs:element type="xs:string" name="name"/>
      <xs:element minOccurs="0" type="xs:string" name="description"/>
    </xs:sequence>
  </xs:complexType>
</xs:schema>
              </textarea>
                        </li>
                        <li>Sample file:&nbsp;</li>
                        <textarea rows="6" cols="140" style="resize: none;" readonly>
<?xml version="1.0" encoding="UTF-8"?>
                            <xs2:server xmlns:xs2="http://www.opsource.net/simpleapp">
  <xs2:id>1</xs2:id>
  <xs2:name>MyServerName</xs2:name>
  <xs2:description>MyServerName</xs2:description>
</xs2:server>
              </textarea>
                    </ul>
                </li>
            </ul>
            <p style="padding-left: 60px;">&nbsp;</p>
            <p>&nbsp;</p></div>    `
})
export class HelpComponent {
}
