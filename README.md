# DataShuffle  

#### SoftwareCide-Squad's solution for data visualisation  

##### How to extend our project with more use cases:

1. Decide on functionality to implement.  
1. Fork our repository.  
1. Go to your website of choice and figure out how to go about grabbing hte data that you have an interest in.        
1. Implement a new webscraper to suit your needs. This will likely be the most challenging step.  
    * Use the webscraper.clever package to deal with sites that need POST requests.  
1. Implement a Getter class, in the retriever package that will help you do all the data retrieval work in another thread while you present the user with a progress bar :).    
1. Add a new button on the splash screen and a corresponding handler and dialog box in the splash.Controller class.  