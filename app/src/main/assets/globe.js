window.onload = function init()
{
    const canvas = document.querySelector("#gl-canvas");
    const renderer = new THREE.WebGLRenderer({canvas});

    // Earth params
    let radius = 0.5,
        segments = 32,
        rotation = 6;

    let scene = new THREE.Scene();
    let camera = new THREE.PerspectiveCamera(45, canvas.width/canvas.clientHeight, 0.01, 1000);
    camera.position.z = 1.5;

    scene.add(new THREE.AmbientLight(0x333333));

    let light = new THREE.DirectionalLight(0xffffff, 1);
    light.position.set(5, 3, 5);
    scene.add(light);

    let sphere = createSphere(radius, segments);
    sphere.rotation.y = rotation;
    scene.add(sphere);
    
    let clouds = createClouds(radius, segments);
    clouds.rotation.y = rotation;
    scene.add(clouds);

    let stars = createStars(90, 64);
    scene.add(stars);

    let controls = new THREE.TrackballControls(camera);

    render();

    function render(){
        controls.update();
        sphere.rotation.y += 0.0005;
        clouds.rotation.y += 0.0005;
        requestAnimationFrame(render);
        renderer.render(scene, camera);
    }

}

function createSphere(radius, segments){
    return new THREE.Mesh(
        new THREE.SphereGeometry(radius, segments, segments),
        new THREE.MeshPhongMaterial({
            map:         new THREE.TextureLoader().load('file:///android_asset/images/2_no_clouds_4k.jpg'),
            bumpMap:     new THREE.TextureLoader().load('file:///android_asset/images/elev_bump_4k.jpg'),
            bumpScale:   0.005,
            specularMap: new THREE.TextureLoader().load('file:///android_asset/images/water_4k.png'),
            specular:    new THREE.Color('gray')
        })
    );
}

//function createSphere(radius, segments){
//    return new THREE.Mesh(
//        new THREE.SphereGeometry(radius, segments, segments),
//        new THREE.MeshPhongMaterial({
//            map:         new THREE.TextureLoader().load('images/2_no_clouds_4k.jpg'),
//            bumpMap:     new THREE.TextureLoader().load('images/elev_bump_4k.jpg'),
//            bumpScale:   0.005,
//            specularMap: new THREE.TextureLoader().load('images/water_4k.png'),
//            specular:    new THREE.Color('gray')
//        })
//    );
//}

function createClouds(radius, segments){
    return new THREE.Mesh(
        new THREE.SphereGeometry(radius + 0.003, segments, segments),
        new THREE.MeshPhongMaterial({
            map:         new THREE.TextureLoader().load('images/fair_clouds_4k.png'),
            transparent: true
        })
    );
}

function createStars(radius, segments){
    return new THREE.Mesh(
        new THREE.SphereGeometry(radius, segments, segments),
        new THREE.MeshBasicMaterial({
            map:  new THREE.TextureLoader().load('images/galaxy_starfield.png'),
            side: THREE.BackSide
        })
    );
}