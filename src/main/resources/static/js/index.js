const spanLeft=document.querySelector('span.left');
const spanRight=document.querySelector('span.right');
const con1=document.querySelector('.con1');
const con2=document.querySelector('.con2');

spanLeft.addEventListener('mouseenter',(event)=>{

  if(con2.classList.contains('on')){
    con2.classList.remove('on');
    con2.classList.add('off');
  }
  if(con1.classList.contains('off')){
    con1.classList.remove('off');
    con1.classList.add('on');
  }

});

spanRight.addEventListener('mouseenter',(event)=>{
  if(con1.classList.contains('on')){
    con1.classList.remove('on');
    con1.classList.add('off');
  }
  if(con2.classList.contains('off')){
    con2.classList.remove('off');
    con2.classList.add('on');
  }

});