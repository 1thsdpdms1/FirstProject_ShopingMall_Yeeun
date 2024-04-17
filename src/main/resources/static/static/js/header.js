const preGnb=document.querySelector('.pre-gnb');
const gnb=document.querySelector('.gnb');

preGnb.addEventListener('mouseenter',(event)=>{
  if(preGnb.classList.contains('on')){
  preGnb.classList.remove('on');
  preGnb.classList.add('off');
}
if(gnb.classList.contains('off')){
  gnb.classList.remove('off');
  gnb.classList.add('on');
}
});
gnb.addEventListener('mouseleave',(evnet)=>{
  event.stopImmediatePropagation()
  if(gnb.classList.contains('on')){
    gnb.classList.remove('on');
    gnb.classList.add('off');
  }
  if(preGnb.classList.contains('off')){
    preGnb.classList.remove('off');
    preGnb.classList.add('on');
  }
})