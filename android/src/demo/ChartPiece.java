package demo;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class ChartPiece extends ApplicationAdapter {
  public PerspectiveCamera cam;
  public Model model;
  public ModelInstance instance;
  public ModelBatch modelBatch;
  Environment environment;
  CameraInputController camController;

  /*final float[] vertices1 = {-4, -1, 0};
  final float[] vertices2 = {7, 5, 0};
  final float[] vertices3 = {1, -6, 0};*/

  final float[] vertices1 = { 0, -5, -1};
  final float[] vertices2 = { 0, 5, -1};
  final float[] vertices3 = { 10, 0, -1};

  final int translationFactor = 1;

  private Array<ModelInstance> modelInstanceArray;

  @Override
  public void create() {
    environment = new Environment();
    environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
    // environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -0.5f, -0.8f, -0.2f));
    modelBatch = new ModelBatch();
    cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    cam.position.set(0f, 0f, 15f);
    cam.lookAt(0, 0, 0);
    cam.near = 1;
    cam.far = 300;
    cam.update();

    camController = new CameraInputController(cam);
    Gdx.input.setInputProcessor(camController);

    modelInstanceArray = new Array<ModelInstance>();

    ModelBuilder modelBuilder = new ModelBuilder();
    modelBuilder.begin();
    MeshPartBuilder meshBuilder;
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    pixmap.setColor(Color.WHITE);
    pixmap.fill();
    Texture texture = new Texture(pixmap);
    modelBuilder.manage(pixmap);
    modelBuilder.manage(texture);
    // Lower triangle
    meshBuilder = modelBuilder.part("lowertriangle", GL20.GL_TRIANGLES,
        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
            | VertexAttributes.Usage.TextureCoordinates,
        new Material(TextureAttribute.createDiffuse(texture),
            ColorAttribute.createSpecular(1, 1, 1, 1), FloatAttribute.createShininess(8f)));
    MeshPartBuilder.VertexInfo info1 = new MeshPartBuilder.VertexInfo();
    info1.setPos(vertices1[0], vertices1[1], vertices1[2]);
    MeshPartBuilder.VertexInfo info2 = new MeshPartBuilder.VertexInfo();
    info2.setPos(vertices2[0], vertices2[1], vertices2[2]);
    MeshPartBuilder.VertexInfo info3 = new MeshPartBuilder.VertexInfo();
    info3.setPos(vertices3[0], vertices3[1], vertices3[2]);
    meshBuilder.triangle(info1, info2, info3);
    meshBuilder.triangle((short) 3, (short) 2, (short) 1);
    Node node = modelBuilder.node();
    node.translation.set(0, 0, translationFactor);

    // Upper triangle
    meshBuilder = modelBuilder.part("uppertriangle", GL20.GL_TRIANGLES,
        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
            | VertexAttributes.Usage.TextureCoordinates,
        new Material(TextureAttribute.createDiffuse(texture),
            ColorAttribute.createSpecular(1, 1, 1, 1), FloatAttribute.createShininess(8f)));
    info1.reset();
    info1.setPos(vertices1[0], vertices1[1], vertices1[2]);
    info2.reset();
    info2.setPos(vertices2[0], vertices2[1], vertices2[2]);
    info3.reset();
    info3.setPos(vertices3[0], vertices3[1], vertices3[2]);
    meshBuilder.triangle(info1, info2, info3);
    meshBuilder.triangle((short) 3, (short) 2, (short) 1);

    // Sides
//    pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//    pixmap.setColor(Color.BLUE);
//    pixmap.fill();
//    texture = new Texture(pixmap);
//    modelBuilder.manage(pixmap);
//    modelBuilder.manage(texture);
//    meshBuilder = modelBuilder.part("line1", GL20.GL_TRIANGLES,
//        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
//            | VertexAttributes.Usage.TextureCoordinates,
//        new Material(TextureAttribute.createDiffuse(texture),
//            ColorAttribute.createSpecular(1, 1, 1, 1), FloatAttribute.createShininess(8f)));
//    info1.reset();
//    info1.setPos(vertices1[0], vertices1[1], vertices1[2] - translationFactor);
//    info2.reset();
//    info2.setPos(vertices1[0], vertices1[1], vertices1[2]);
//    info3.reset();
//    info3.setPos(vertices2[0], vertices2[1], vertices2[2]);
    MeshPartBuilder.VertexInfo info4 = new MeshPartBuilder.VertexInfo();
//    info4.setPos(vertices2[0], vertices2[1], vertices2[2] - translationFactor);
//    meshBuilder.rect(info1, info2, info3, info4);

    meshBuilder = modelBuilder.part("line2", GL20.GL_TRIANGLES,
        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
            | VertexAttributes.Usage.TextureCoordinates,
        new Material(TextureAttribute.createDiffuse(texture),
            ColorAttribute.createSpecular(1, 1, 1, 1), FloatAttribute.createShininess(8f)));
    info1.reset();
    info1.setPos(vertices2[0], vertices2[1], vertices2[2] - translationFactor);
    info2.reset();
    info2.setPos(vertices2[0], vertices2[1], vertices2[2]);
    info3.reset();
    info3.setPos(vertices3[0], vertices3[1], vertices3[2]);
    info4.reset();
    info4.setPos(vertices3[0], vertices3[1], vertices3[2] - translationFactor);
    meshBuilder.rect(info1, info2, info3, info4);

    meshBuilder = modelBuilder.part("line3", GL20.GL_TRIANGLES,
        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal
            | VertexAttributes.Usage.TextureCoordinates,
        new Material(TextureAttribute.createDiffuse(texture),
            ColorAttribute.createSpecular(1, 1, 1, 1), FloatAttribute.createShininess(8f)));
    info1.reset();
    info1.setPos(vertices3[0], vertices3[1], vertices3[2] - translationFactor);
    info2.reset();
    info2.setPos(vertices3[0], vertices3[1], vertices3[2]);
    info3.reset();
    info3.setPos(vertices1[0], vertices1[1], vertices1[2]);
    info4.reset();
    info4.setPos(vertices1[0], vertices1[1], vertices1[2] - translationFactor);
    meshBuilder.rect(info1, info2, info3, info4);


    model = modelBuilder.end();
    instance = new ModelInstance(model);

    modelInstanceArray.add(instance);
    long attr = VertexAttributes.Usage.Normal | VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates ;
    Model cylinderModel = modelBuilder.createCylinder(vertices2[1] - vertices1[1], translationFactor, 2f, 100, new Material(ColorAttribute.createDiffuse(Color.WHITE)), attr, 0, 180);

    ModelInstance cyModelInstance = new ModelInstance(cylinderModel);
    cyModelInstance.transform.translate(0f, 0f, -(translationFactor/2.0f));
    cyModelInstance.transform.rotate(Vector3.Y, -90);
    cyModelInstance.transform.rotate(Vector3.Z,  90);
    modelInstanceArray.add(cyModelInstance);

  }

  @Override
  public void render() {
    Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

    cam.update();
    modelBatch.begin(cam);
    modelBatch.render(modelInstanceArray, environment);
    modelBatch.end();
  }

  @Override
  public void dispose() {
    model.dispose();
  }
}
