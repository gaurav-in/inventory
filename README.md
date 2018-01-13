# Inventory Management

<ol>
<li>Test case SmsServiceTest will be used to enter the application program.</li>
<li>The list of all the input commands is used to dispay the input in the end.</li>
<li><b>CommandExecutor uses a thread to execte the tasks added to the queue.</b></li>
</ol>
<ol><b>Flow of the application</b>
<li>Command create itemName costPrice sellingPrice</li>
<li>Creates a CreateCommand Object (Uses inventory management service to carry out the command)</li>
<li>The object is submitted for execution by CommandExecutor</li>
<li>Objects store data in static collections to be able to be available for all the objects</li>
</ol>
