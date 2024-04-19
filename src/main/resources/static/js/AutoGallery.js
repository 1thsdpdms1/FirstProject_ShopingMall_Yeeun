const autoGallery= document.querySelector('.autoGallery');
const autoGalleryli=document.querySelectorAll('.autoGallery>ul>li');

const items=document.querySelector('.items');
const itemsli= document.querySelectorAll('.items>ul>li');

let i=-1;

function autoGalleryFn(){
    i++;

    console.log(`i=${i}`);

    autoGalleryli.forEach((el,idx)=>{
        if(idx==i){
            autoGalleryli[idx].classList.add('fadeIn');

        }else{
            autoGalleryli[idx].classList.remove('fadeIn');
        }
    });
    itemsli.forEach((el,idx)=>{
        if(idx==i){
            itemsli[idx].classList.add('on');
        }else{
            itemsli[idx].classList.remove('on');
        }
    })
    if(autoGalleryli.length-1<=i) i=-1;
}

//3초마다 autoGallery 함수를 실행
let setIn=setInterval(autoGalleryFn,3000);
    const spanArrows=document.querySelectorAll('span.arrow');

    spanArrows.forEach((el,idx)=>{
        el.addEventListener('mouseover',arrowFn);
        el.addEventListener('mouseout',arrowFn);
    });

    function arrowFn(event){
        console.log(event.type);
        if(event.type=="mouseover"){
            clearInterval(setIn) //중지 마우스 오버시
        }else if(event.type=="mouseout"){
        setIn=setInterval(autoGalleryFn,3000); //마우스를 올렸을 때 다시 실행
        }
    }

    itemsli.forEach(el=>{
        el.addEventListener('mouseover',itemsLiFn);
        el.addEventListener('mouseout',itemsLiFn);
    });
    function itemsLiFn(event){
        console.log(event.type)
        if(event.type=="mouseover"){
        clearInterval(setIn)//중지 마우스 오버시
        }else if(event.type=="mouseout"){
        setIn=setInterval(autoGalleryFn,3000);
        }
    }