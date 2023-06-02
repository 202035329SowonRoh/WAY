let gl;
const maxNumVertices = 3 * 200;
let index = 0;
var lampPoint = [];
var randSnow = [];
var lColors = [];
var xAxis = 0;
var yAxis = 1;
var zAxis = 2;
var axis = 0;
var theta = [ 0, 0, 0 ];
let thetaLoc;
let flag = false;

window.onload = function init()
{
    let canvas = document.getElementById("gl-canvas");
    gl = WebGLUtils.setupWebGL( canvas );
    if (!gl) { alert("WebGL isn't available") };

    // use 2 array for background, two triangles
    let background = [
        vec2(-1, 1),
        vec2(-1, -1),
        vec2(1, 1),
    ];
    let background2 = [
        vec2(1, -1),
        vec2(-1, -1),
        vec2(1, 1)
    ];

    let colors = [
        // back-ground colors
        vec4(0.5, 0.3, 0.5, 1.0),
        vec4(0.3, 0.5, 0.3, 1.0),
        vec4(0.2, 0.35, 0.3, 1.0)
    ];

    let ground = [
        vec2(-1, -0.55),
        vec2(-1, -1),
        vec2(1, -0.55),
        vec2(1, -1)
    ];

    let house = [
        // house's body
        vec2(-1, 0.1),
        vec2(-1, -0.55),
        vec2(-0.2, 0.1),
        vec2(-0.2, -0.55),
        // house's roof
        vec2(-0.65, 0.65),
        vec2(-1.4, 0.1),
        vec2(0.1, 0.1),
        // house's chimney of funnel
        vec2(-0.97, 0.65),
        vec2(-0.97, 0.35),
        vec2(-0.81, 0.65),
        vec2(-0.81, 0.35),
        // house's window
        vec2(-0.95, 0.05),
        vec2(-0.95, -0.2),
        vec2(-0.68, 0.05),
        vec2(-0.68, -0.2),
        // house's frame of window
        vec2(-0.95, -0.2),
        vec2(-0.95, 0.05),
        vec2(-0.68, 0.05),
        vec2(-0.95, -0.2),
        vec2(-0.68, -0.2),
        vec2(-0.68, 0.05)
    ];

    let tree = [
        // tree's body
        vec2(-0.07, -0.3),
        vec2(-0.07, -0.6),
        vec2(0.07, -0.3),
        vec2(0.07, -0.6),
        // tree's leaf
        vec2(-0.01, -0.13),
        vec2(-0.2, -0.3),
        vec2(0.2, -0.3)
    ];

    let streetlamp = [
        // street lamp's body
        vec2(0.18, -0.55),
        vec2(0.18, -0.1),
        vec2(0.235, -0.55),
        vec2(0.235, -0.1),
        vec2(0.16, -0.12),
        vec2(0.2025, -0.05),
        vec2(0.255, -0.12)
    ];

    let snowman = [
        // snowman's head
        vec2(0.0, 0.0),    // V0
        vec2(0.2, 0.0),    // V1
        vec2(0.14, 0.14),  // V2
        vec2(0.0, 0.2),    // V3
        vec2(-0.14, 0.14), // V4
        vec2(-0.2, 0.0),   // V5
        vec2(-0.14, -0.14),// V6
        vec2(0.0, -0.2),   // V7
        vec2(0.14, -0.14), // V8
        vec2(0.2, 0.0),    // V9 
        // snowman's eyes, nose and button
        vec2(0.07, 0.055),  // eye
        vec2(-0.07, 0.055), // eye
        vec2(0.0, -0.01),   // nose
        vec2(0.0, -0.25),   // button
        vec2(0.0, -0.35),   // button
        vec2(0.0, -0.45),   // button
        // snowman's name (JH)
        vec2(-0.15, -0.3),
        vec2(-0.05, -0.3),
        vec2(-0.08, -0.3),
        vec2(-0.08, -0.43),
        vec2(-0.15, -0.43),
        vec2(-0.08, -0.43),
        vec2(0.06, -0.29),
        vec2(0.06, -0.435),
        vec2(0.06, -0.362),
        vec2(0.15, -0.362),
        vec2(0.15, -0.29),
        vec2(0.15, -0.435)
    ];
    // snowman's body
    // 135% more bigger about snowman's head
    let snowmanBody =  snowman.map( x => vec2( x[0]*1.35, x[1]*1.3-0.35 ) );

    // Configure WebGL 
    gl.viewport(0, 0, canvas.width, canvas.height);
    gl.clearColor(0.0, 0.0, 0.0, 1.0);

    // Load shaders and initialize attribute buffers
    let program = initShaders(gl, "vertex-shader", "fragment-shader");
    gl.useProgram( program );

    // Get elements Attribute and Uniform
    let vPosition = gl.getAttribLocation(program, "vPosition");
    let uOffset = gl.getUniformLocation(program, "uOffset");
    let treePosition = gl.getUniformLocation(program, "treePosition");  // Only use for tree's leaf position
    let vColor = gl.getAttribLocation(program, "vColor");
    thetaLoc = gl.getUniformLocation(program, "theta");
    let lamp1 = gl.getUniformLocation(program, "lamp1")
    let lamp2 = gl.getUniformLocation(program, "lamp2")


    // When click the canvas, a snow will appear temporary
    canvas.addEventListener("click", function(event){
        gl.uniform4fv( uOffset, [randomInt(), randomInt(), 0.0, 0.0] );
        let mySnow = sphere(5);
        mySnow.scale(0.05, 0.05, 0.05);
        mySnow.rotate(45, [1,1,1]);
        randSnow = randSnow.concat(mySnow.TriangleVertices);

        let vBuffer = gl.createBuffer();
        gl.bindBuffer( gl.ARRAY_BUFFER, vBuffer );
        gl.bufferData(gl.ARRAY_BUFFER, flatten(randSnow), gl.STATIC_DRAW);
        gl.vertexAttribPointer(vPosition, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(vPosition);
        gl.vertexAttrib4fv(vColor, [1.0, 1.0, 1.0, 1.0]);
        gl.drawArrays(gl.TRIANGLES, 0, randSnow.length);
    })
    function randomInt(){ return Math.random() * 2 -1; }


    // Draw background
    function DrawBackground(){
        noRotation();

        let bufferId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, bufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(background), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        let colorBufferId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, colorBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vColor );
        gl.drawArrays(gl.TRIANGLES, 0, 3);

        let bufferId2 = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, bufferId2);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(background2), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        gl.bindBuffer(gl.ARRAY_BUFFER, colorBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(colors), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vColor, 4, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vColor );
        gl.drawArrays(gl.TRIANGLES, 0, 3);
        gl.disableVertexAttribArray(vColor);
        gl.uniform4fv( uOffset, [0.0, 0.0, 0.0, 0.0] );
    }

    // Draw white ground with snow
    function DrawGround(){
        noRotation();
        let groundBufferId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, groundBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(ground), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        // Redefine the vColor
        gl.disableVertexAttribArray( vColor );
        gl.vertexAttrib4fv( vColor, [0.7, 0.9, 0.95, 1.0] );
        gl.drawArrays(gl.TRIANGLE_STRIP, 0, 4);
        gl.disableVertexAttribArray(vColor);
        gl.uniform4fv( uOffset, [0.0, 0.0, 0.0, 0.0] );
    }
    

    // Draw the house
    function DrawHouse(){
        noRotation();
        let houseBufferId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, houseBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(house), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        // house's frame of window
        gl.vertexAttrib4fv( vColor, [0.0, 0.0, 0.0, 0.8]);
        gl.drawArrays(gl.LINE_STRIP, 15, 6);
        // house's window
        gl.vertexAttrib4fv( vColor, [0.4, 0.6, 0.9, 0.8]);
        gl.drawArrays(gl.TRIANGLE_STRIP, 11, 4);
        // house's body
        gl.vertexAttrib4fv( vColor, [0.65, 0.55, 0.0, 1.0]);
        gl.drawArrays(gl.TRIANGLE_STRIP, 0, 4);
        // house's roof
        gl.vertexAttrib4fv( vColor, [0.85, 0.2, 0.1, 0.8]);
        gl.drawArrays(gl.TRIANGLES, 4, 3);
        // house's chimney
        gl.vertexAttrib4fv( vColor, [0.15, 0.15, 0.15, 0.9]);
        gl.drawArrays(gl.TRIANGLE_STRIP, 7, 4);
        gl.disableVertexAttribArray(vColor);
        gl.uniform4fv( uOffset, [0.0, 0.0, 0.0, 0.0] );
    }
    

    // Draw the street lamp's body
    function DrawLampBody(){
        noRotation();
        let streetlampId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, streetlampId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(streetlamp), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        gl.vertexAttrib4fv( vColor, [0.5, 0.5, 0.5, 1.0]);
        gl.drawArrays(gl.TRIANGLE_STRIP, 0, 4);
        gl.drawArrays(gl.TRIANGLES, 4, 3);
        gl.disableVertexAttribArray(vColor);
        gl.uniform4fv( uOffset, [0.0, 0.0, 0.0, 0.0] );
    }


    // Draw the tree
    function DrawTree(){
        noRotation();
        let treeBufferId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, treeBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(tree), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        // tree's body
        gl.vertexAttrib4fv( vColor, [0.6, 0.25, 0.1, 1.0] );
        gl.drawArrays(gl.TRIANGLE_STRIP, 0, 4);
        // tree's leaves
        gl.vertexAttrib4fv( vColor, [0.1, 0.9, 0.1, 1.0] );
        gl.drawArrays(gl.TRIANGLES, 4, 3);
        gl.uniform4fv( treePosition, [0.0, 0.17, 0.0, 0.0] );
        gl.drawArrays(gl.TRIANGLES, 4, 3);
        gl.uniform4fv( treePosition, [0.0, 0.34, 0.0, 0.0] );
        gl.drawArrays(gl.TRIANGLES, 4, 3);
        gl.uniform4fv( treePosition, [0.0, 0.34, 0.0, 0.0] );
        gl.drawArrays(gl.TRIANGLES, 4, 3);
        gl.uniform4fv( treePosition, [0.0, 0.0, 0.0, 0.0] );
        gl.disableVertexAttribArray(vColor);
    }
    function DrawTrees(){
        gl.uniform4fv( uOffset, [-0.65, -0.27, 0.0, 0.0] );
        DrawTree();
        gl.uniform4fv( uOffset, [-0.35, -0.35, 0.0, 0.0] );
        DrawTree();
        gl.uniform4fv( uOffset, [-0.07, -0.15, 0.0, 0.0] );
        DrawTree();
        gl.uniform4fv( uOffset, [0.85, 0.0, 0.0, 0.0] );
        DrawTree();
        gl.uniform4fv( uOffset, [0.0, 0.0, 0.0, 0.0] );
    }
    

    // Draw snow-man
    function DrawSnowman1(){
        noRotation();
        // gl.enable(gl.DEPTH_TEST);
        gl.uniform4fv( uOffset, [0.5, -0.2, 0.0, 0.0] );
        let snowmanBufferId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, snowmanBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(snowmanBody), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        // snowman's body
        gl.vertexAttrib4fv( vColor, [0.8, 1.0, 1.0, 1.0] );
        gl.drawArrays(gl.TRIANGLE_FAN, 0, 10);
        // snowman's head
        gl.bindBuffer(gl.ARRAY_BUFFER, snowmanBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(snowman), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        gl.vertexAttrib4fv( vColor, [0.8, 1.0, 1.0, 1.0] );
        gl.drawArrays(gl.TRIANGLE_FAN, 0, 10);
    }
    function DrawSnowman2(){
        gl.uniform4fv( uOffset, [0.5, -0.2, 0.0, 0.0] );
        // snowman's eyes and nose
        let sibal = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, sibal);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(snowman), gl.STATIC_DRAW);
        gl.vertexAttribPointer( vPosition, 2, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( vPosition );
        gl.vertexAttrib4fv( vColor, [0.0, 0.0, 0.0, 1.0] );
        gl.drawArrays(gl.POINTS, 10, 1);
        gl.drawArrays(gl.POINTS, 11, 1);
        gl.vertexAttrib4fv( vColor, [0.8, 0.2, 0.0, 1.0] );
        gl.drawArrays(gl.POINTS, 12, 1);
        // snowman's button
        gl.vertexAttrib4fv( vColor, [0.4, 0.7, 0.7, 1.0] );
        gl.drawArrays(gl.POINTS, 13, 1);
        gl.drawArrays(gl.POINTS, 14, 1);
        gl.drawArrays(gl.POINTS, 15, 1);
        // snowman's name (JH = JUHA)
        gl.vertexAttrib4fv( vColor, [0.0, 0.0, 0.0, 1.0] );
        gl.drawArrays(gl.LINES, 16, 2);
        gl.drawArrays(gl.LINES, 18, 2);
        gl.drawArrays(gl.LINES, 20, 2);
        gl.drawArrays(gl.LINES, 22, 2);
        gl.drawArrays(gl.LINES, 24, 2);
        gl.drawArrays(gl.LINES, 26, 2);
    }
    function DrawSnowman(){
        DrawSnowman2();
        DrawSnowman1();
        gl.uniform4fv( uOffset, [0.0, 0.0, 0.0, 0.0] );
        gl.disableVertexAttribArray(vColor);
    }

    
    // Draw Street lamp
    // The Lamp head can be rotated.
    function DrawLamp(){
        rotation();
        if(flag)theta[axis] += 2.0;
        gl.uniform3fv(thetaLoc, theta);
        gl.uniform4fv(uOffset, [0.206, -0.03, -0.3, 0.0]);
        gl.enable(gl.DEPTH_TEST);

        let mySphere = sphere(5);
        mySphere.scale(0.08, 0.08, 0.08);
        mySphere.rotate(45, [1,1,1]);
        lColors = lColors.concat(mySphere.TriangleVertexColors);
        lampPoint = lampPoint.concat(mySphere.TriangleVertices);
        
        let lampBufferId = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, lampBufferId);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(lampPoint), gl.STATIC_DRAW);
        gl.vertexAttribPointer(vPosition, 4, gl.FLOAT, false, 0, 0);
        gl.enableVertexAttribArray(vPosition);
        let lampColor = gl.createBuffer();
        gl.bindBuffer(gl.ARRAY_BUFFER, lampColor);
        gl.bufferData(gl.ARRAY_BUFFER, flatten(lColors), gl.STATIC_DRAW);
        let cBuffer = gl.getAttribLocation(program, 'vColor');
        gl.vertexAttribPointer( cBuffer, 4, gl.FLOAT, false, 0, 0 );
        gl.enableVertexAttribArray( cBuffer );

        document.getElementById("xButton").onclick = function() {
            axis = xAxis;
        };
        document.getElementById("yButton").onclick = function() {
            axis = yAxis;
        };
        document.getElementById("zButton").onclick = function() {
            axis = zAxis;
        };
        document.getElementById("ButtonT").onclick = function() {
            flag = !flag;
        }
        gl.drawArrays(gl.TRIANGLES, 0, lampPoint.length);
        gl.disableVertexAttribArray(vColor);
        gl.uniform4fv( uOffset, [0.0, 0.0, 0.0, 0.0] );
    }

    
    function render() {
        DrawSnowman();
        DrawLampBody();
        DrawLamp();
        DrawTrees();
        DrawGround();
        DrawHouse();
        DrawBackground();
        requestAnimationFrame(render);
    }

    render();


    // function for rotataing or not
    function noRotation(){
        gl.uniformMatrix4fv(lamp1, false,
            [
                1.0, 0.0, 0.0, 0.0,
                0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0,
                0.0, 0.0, 0.0, 1.0
            ])
        gl.uniformMatrix4fv(lamp2, false, 
            [
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0 
            ])
    }
    function rotation(){
        gl.uniformMatrix4fv(lamp2, false,
            [
                1.0, 0.0, 0.0, 0.0,
                0.0, 1.0, 0.0, 0.0,
                0.0, 0.0, 1.0, 0.0,
                0.0, 0.0, 0.0, 1.0
            ])
        gl.uniformMatrix4fv(lamp1, false, 
            [
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0 
            ])
    }
}
