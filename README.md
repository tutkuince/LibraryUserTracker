# LibraryUserTracker
<hr/>
The aim of this project is to review basic CRUD processes with JavaServerPages framework.

There are 6 layers in this project:
<br />
<ul>
  <li>
    <b>Model</b> represents an object or JAVA POJO carrying data.
  </li>
  <li>
    <b>View</b> represents the visualization of the data that model contains. (JSP)
  </li>
  <li>
    <b>Controller</b> acts on both model and view. It controls the data flow into model object and updates the view whenever data changes. It keeps view and model separate. (Servlet)
  </li>
  <li>
    <b>DAO</b> pattern is used to separate low level data accessing API or operations from high level business services. I create a Generic Inteface for basic CRUD process that can be easily implementable.
  </li>
  <li>
    <b>Test</b> layer for unit tests.
  </li>
  <li>
    <b>Util</b> layer contains JDBCUtil class that helps us to get Connection object with Singleton Design Pattern.
  </li>
</ul>

<h2></h2>
<img src="https://www.ibm.com/support/knowledgecenter/SSRTLW_9.1.1/com.ibm.etools.struts.doc/images/cstrdoc001a.gif" />
