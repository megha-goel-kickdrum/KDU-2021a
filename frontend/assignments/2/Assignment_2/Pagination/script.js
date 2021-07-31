function showPage(id)
{
   var totalNumberOfpages = 6;
   for(i=1;i<= totalNumberOfpages; i++)
   {
       if( i == id)
       {
           document.getElementById('page'+i).style.display='';
       }

    else{
        document.getElementById('page'+i).style.display='none';   
    }
   }

}



function prevPage()
{
   var totalNumberOfpages = 6;

   if(id !== 1)
   {
    for(i=1;i<= totalNumberOfpages; i++)
    {
        if( i == id)
        {
            document.getElementById('page'+(i-1)).style.display='';
        }
 
     else{
         document.getElementById('page'+i).style.display='none';   
     }
    }
   }

}

function nextPage()
{
   var totalNumberOfpages = 6;

   if(id !== 6)
   {
    for(i=1;i<= totalNumberOfpages; i++)
    {
        if( i == id)
        {
            document.getElementById('page'+(i+1)).style.display='';
        }
 
     else{
         document.getElementById('page'+i).style.display='none';   
     }
    }
   }

}

function searchName()
{
    var input, ul, li, i, txt, filter;
    input = document.getElementById('search');
    filter = input.value.toUpperCase();
    ul = ul.getElementsByTagName('ul');
    li = ul.getElementsByTagName('li');

    for (i = 0; i < li.length; i++) {
        a = li[i];
        txt = a.textContent || a.innerText;
        if (txt.toUpperCase().indexOf(filter) > -1) {
          document.style.display = a.innerText;
        } else {
          document.style.display = "none";
        }
      }


}


