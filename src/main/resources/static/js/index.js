const spanLeft=document.querySelector('span.left');
const spanRight=document.querySelector('span.right');
const con1=document.querySelector('.con1');
const con2=document.querySelector('.con2');

spanLeft.addEventListener('mouseenter',(event)=>{

  if(con2.classList.contains('span-on2')){
    con2.classList.remove('span-on2');
    con2.classList.add('span-off');
  }
  if(con1.classList.contains('span-off')){
    con1.classList.remove('span-off');
    con1.classList.add('span-on');
  }

});

spanRight.addEventListener('mouseenter',(event)=>{
  if(con1.classList.contains('span-on')){
    con1.classList.remove('span-on');
    con1.classList.add('span-off');
  }
  if(con2.classList.contains('span-off')){
    con2.classList.remove('span-off');
    con2.classList.add('span-on2');
  }

});
